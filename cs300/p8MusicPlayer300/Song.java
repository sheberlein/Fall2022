//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO8 MusicPlayer300 - Song class
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is a representation of a single Song. Interfaces with the provided AudioUtility class,
 * which uses the javax.sound.sampled package to play audio to your computer's audio output device
 * @author Sidney Heberlein
 *
 */
public class Song 
{
  // instance fields
  private AudioUtility audioClip; // This song's AudioUtility interface to javax.sound.sampled
  private String title; // The title of this song
  private String artist; // The artist of this song
  private int duration; // The duration of this song in number of seconds
  
  // constructor
  
  /**
   * This method initializes all instance data fields according to the provided values
   * @param title   the title of the song, set to empty string if null
   * @param artist   the artist of this song, set to empty string if null
   * @param filepath   the full relative path to the song file, begins with the "audio" directory 
   * for P08
   * @throws IllegalArgumentException   if the song file cannot be read
   */
  public Song(String title, String artist, String filepath) throws IllegalArgumentException
  {
    try
    {
      audioClip = new AudioUtility(filepath);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException("The file cannot be read.");
    }
    if (title == null)
    {
      this.title = "";
    }
    else
    {
      this.title = title;
    }
    if (artist == null)
    {
      this.artist = "";
    }
    else
    {
      this.artist = artist;
    }
    duration = audioClip.getClipLength();
  }
  
  // methods
  
  /**
   * This method tests whether this song is currently playing using the AudioUtility
   * @return   true if the song is playing, false otherwise
   */
  public boolean isPlaying()
  {
    if (audioClip.isRunning())
    {
      return true; // return true if the audio clip is currently running
    }
    return false; // return false if the audio clip is not currently running
  }
  
  /**
   * This method is the accessor method for the song's title
   * @return   the title of this song
   */
  public String getTitle()
  {
    return title;
  }
  
  /**
   * This method is the accessor method for the song's artist
   * @return   the artist of this song
   */
  public String getArtist()
  {
    return artist;
  }
  
  /**
   * This method uses the AudioUtility to start playback of this song, reopening the clip for
   * playback if necessary
   */
  public void play()
  {
    if (!audioClip.isReadyToPlay())
    {
      audioClip.reopenClip(); // if the clip isn't ready to play, reopen the clip so that it is
    }
    audioClip.startClip(); // start the clip
    System.out.println("Playing ..." + this.toString());
  }
  
  /**
   * This method uses the AudioUtility to stop playback of this song
   */
  public void stop()
  {
    audioClip.stopClip();
  }
  
  /**
   * This method creates and returns a string representation of this Song, for example:
   * "Africa" (4:16) by Toto
   * The title should be in quotes, the duration should be split out into minutes and seconds 
   * (recall it is stored as seconds only!), and the artist should be preceded by the word "by". It
   * is intended for this assignment to leave single-digit seconds represented as 0:6, for example, 
   * but if you would like to represent them as 0:06, this is also allowed.
   * @return   a formatted string representation of this Song
   */
  @Override
  public String toString()
  {
    int mins = duration/60; // this will give the number of minutes as an integer
    int secs = duration - (mins*60); // this will give the number of seconds (not including mins)
    String returnThis = "\"" + title + "\" (" + mins + ":" + secs + ") by " + artist;
    return returnThis;
  }
}