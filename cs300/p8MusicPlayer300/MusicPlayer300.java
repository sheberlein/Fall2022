//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO8 MusicPlayer300 - MusicPlayer300 class
// Course:   CS 300 Fall 2022
//
// Author:   Sidney Heberlein
// Email:    sheberlein@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class is a linked-queue based music player which plays Actual Music Files based on keyboard
 * input in an interactive console method. 
 * This music player can load playlists of music or add individual song files to the queue.
 * @author Sidney Heberlein
 *
 */
public class MusicPlayer300 
{
  // instance fields
  private Playlist playlist; // the current playlist of Songs
  private boolean filterPlay; // whether the current playback mode should be filtered by artist; 
  // false by default
  private String filterArtist; // the artist to play if filterPlay is true; should be null otherwise
  
  // constructor
  
  /**
   * This creates a new MusicPlayer300 with an empty playlist
   */
  public MusicPlayer300()
  {
    playlist = new Playlist();
    filterPlay = false;
    filterArtist = null;
  }
  
  // methods
  
  /**
   * This method stops any song that is playing and clears out the playlist
   */
  public void clear()
  {
    while (!playlist.isEmpty())
    {
      playlist.dequeue();
    }
  }
  
  /**
   * This method loads a playlist from a provided file, skipping any individual songs which cannot
   * be loaded. Note that filenames in the provided files do NOT include the audio directory, and 
   * will need that added before they are loaded. Print "Loading" and the song's title in quotes 
   * before you begin loading a song, and an "X" if the load was unsuccessful for any reason.
   * @param file   the File object to load
   * @throws FileNotFoundException   if the playlist file cannot be loaded
   */
  public void loadPlaylist(File file) throws FileNotFoundException
  {
    Scanner s = null; // a new scanner that will be used to read the file
    try
    {
      s = new Scanner(file);
      boolean skip = false; // this boolean tells if you should skip the line
      while (s.hasNextLine())
      {
        String nextLine = s.nextLine(); // a string storing the next line in the file
        if (!nextLine.isBlank())
        {
          int firstComma = nextLine.indexOf(","); // the index of the first comma in the string
          if (firstComma == -1) // if there is not a first comma, skip this line
          {
            skip = true;
          }
          if (skip == false) // if there was a first comma, continue reading the line
          {
            String title = nextLine.substring(0, firstComma); // the title of the song
            nextLine = nextLine.substring(firstComma + 1); // a substring of the line minus the
            // author
            int secondComma = nextLine.indexOf(","); // the index of the second comma in the string
            if (secondComma == -1) // if there is not a second comma, skip this line
            {
              skip = true;
            }
            if (skip == false) // if there was a second comma, continue reading the line
            {
              String author = nextLine.substring(0, secondComma); // the author of the song
              nextLine = nextLine.substring(secondComma + 1); // this should be the filepath string
              String filepath = "audio//" + nextLine; // stores the filepath string
              try
              {
                System.out.println("Loading \"" + title + "\"");
                Song song = new Song(title, author, filepath);
                playlist.enqueue(song);
              }
              catch (IllegalArgumentException e)
              {
                skip = true; // if the filepath could not be loaded, 
              }
            }
          }
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("X"); // print X if the load was unsuccessful
      throw new FileNotFoundException("The file could not be loaded.");
    }
    finally
    {
      if (s != null)
      {
        s.close();
      }
    }
  }
  
  /**
   * This method loads a single song to the end of the playlist given the title, artist, and
   * filepath. Filepaths for P08 must refer to files in the audio directory.
   * @param title   the title of the song
   * @param artist   the artist of this song
   * @param filepath   the full relative path to the song file, begins with the "audio" directory
   * for P08
   * @throws IllegalArgumentException   if the song file cannot be read
   */
  public void loadOneSong(String title, String artist, String filepath)
      throws IllegalArgumentException
  {
    Song s = new Song(title, artist, filepath);
    playlist.enqueue(s);
  }
  
  /**
   * This method provides a string representation of all songs in the current playlist
   * @return   a string representation of all songs in the current playlist
   */
  public String printPlaylist()
  {
    return playlist.toString();
  }
  
  /**
   * This method creates and returns the menu of options for the interactive console program.
   * @return   the formatted menu String
   */
  public String getMenu()
  {
    String menu = "Enter one of the following options:\n[A <filename>] to enqueue a new song file "
        + "to the end of this playlist\n[F <filename>] to load a new playlist from the given file\n"
        + "[L] to list all songs in the current playlist\n[P] to start playing ALL songs in the "
        + "playlist from the beginning\n[P -t <Title>] to play all songs in the playlist starting "
        + "from <Title>\n[P -a <Artist>] to start playing only the songs in the playlist by Artist"
        + "\n[N] to play the next song\n[Q] to stop playing music and quit the program";
    return menu;
  }
  
  /**
   * This method stops playback of the current song (if one is playing) and advances to the next
   * song in the playlist.
   * @throws IllegalStateException   if the playlist is null or empty, or becomes empty at any time
   * during this method
   */
  public void playNextSong() throws IllegalStateException
  {
    if (playlist == null || playlist.isEmpty())
    {
      throw new IllegalStateException("The playlist is null or empty.");
    }
    if (playlist.peek().isPlaying())
    {
      playlist.peek().stop(); // if the first song in the queue is playing, stop playing it
    }
    playlist.dequeue();
    if (playlist == null || playlist.isEmpty())
    {
      throw new IllegalStateException("The playlist is null or empty.");
    }
    if (filterPlay == true)
    {
      Song curr = playlist.peek(); // the first song in the playlist
      while (curr != null)
      {
        if (curr.getArtist().equals(filterArtist))
        {
          curr.play(); // play the song if the artist matched
          break;
        }
        else
        {
          playlist.dequeue(); // remove the song from the playlist
          curr = playlist.peek();
        }
      }
    }
    else
    {
      playlist.peek().play();
    }
    if (playlist == null || playlist.isEmpty())
    {
      throw new IllegalStateException("The playlist is null or empty.");
    }
  }
  
  /**
   * This method is the interactive method to display the MusicPlayer300 menu and get keyboard
   * input from the user. See writeup for details.
   * @param in   a scanner hooked up to System.in
   */
  public void runMusicPlayer300(Scanner in) throws FileNotFoundException
  {
    String input = "";
    Scanner scan = in;
    while (!input.equals("Q"))
    {
      System.out.println(getMenu());
      System.out.println("> ");
      input = scan.nextLine();
      if (input.contains("A ")) // good
      {
        System.out.println("Enter the title of the song.");
        System.out.println("> ");
        String title = scan.next();
        scan.nextLine();
        System.out.println("Enter the artist of the song.");
        System.out.println("> ");
        String artist = scan.next();
        scan.nextLine();
        input = input.substring(2); // gets only the file path name
        Song song = new Song(title, artist, input);
        playlist.enqueue(song);
      }
      else if (input.contains("F "))
      {
        input = input.substring(2); // gets only the file name
        System.out.println("input: " + input);
        try
        {
          loadPlaylist(new File(input));
        }
        catch (FileNotFoundException e)
        {
          throw new FileNotFoundException("The given file was not found.");
        }
      }
      else if (input.equals("L"))
      {
        System.out.println(playlist.toString());
      }
      else if (input.equals("P"))
      {
        if (playlist.isEmpty())
        {
          System.out.println("No songs left :(");
        }
        else
        {
          playlist.peek().play(); // play the first song in the playlist
        }
      }
      else if (input.contains("P -t "))
      {
        if (playlist.isEmpty())
        {
          System.out.println("No songs left :(");
        }
        else
        {
          String title = input.substring(input.indexOf("P -t ") + 5); // the title given by the user
          Song curr = playlist.peek(); // the first song in the playlist
          while (curr != null)
          {
            if (curr.getTitle().equals(title))
            {
              curr.play(); // play the song if the title matched
              break; // break out of the loop
            }
            playlist.dequeue(); // remove the song from the playlist
            curr = playlist.peek(); // the new first song in the playlist after dequeue
          }
          if (curr == null)
          {
            System.out.println("No songs left :(");
          }
        }
      }
      else if (input.contains("P -a "))
      {
        if (playlist.isEmpty())
        {
          System.out.println("No songs left :(");
        }
        else
        {
          filterPlay = true;
          filterArtist = input.substring(input.indexOf("P -a ") + 5); // the author given by user
          Song curr = playlist.peek(); // the first song in the playlist
          while (curr != null)
          {
            if (curr.getArtist().equals(filterArtist))
            {
              curr.play(); // play the song if the artist matched
              break; // break out of the loop
            }
            playlist.dequeue(); // remove the song from the playlist
            curr = playlist.peek();
          }
          if (curr == null)
          {
            System.out.println("No songs left :(");
          }
        }
      }
      else if (input.equals("N"))
      {
          if (playlist.peek().isPlaying())
          {
            playlist.peek().stop();
          }
          playlist.dequeue();
          if (filterPlay == true)
          {
            Song curr = playlist.peek(); // the first song in the playlist
            while (curr != null)
            {
              if (curr.getArtist().equals(filterArtist))
              {
                curr.play(); // play the song if the artist matched
                break; // break out of the loop
              }
              playlist.dequeue(); // remove the song from the playlist
              curr = playlist.peek();
            }
            if (curr == null)
            {
              System.out.println("No songs left :(");
            }
          }
          else
          {
            playlist.peek().play();
          }
      }
      else if (input.equals("Q"))
      {
        System.out.println("Goodbye!");
      }
      else
      {
        System.out.println("I don't know how to do that.");
      }
    }
  }
  
  /**
   * Main method for the MusicPlayer300 class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) throws FileNotFoundException
  {
    MusicPlayer300 player = new MusicPlayer300();
    player.runMusicPlayer300(new Scanner(System.in));
  }
}
