//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Game 2.0
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class DragonTreasureGame extends PApplet
{
  private ArrayList<Room> roomList; // holds a list of rooms for the game
  private File roomInfo; // holds the room information
  private File mapInfo; // holds the map information
  private ArrayList<Character> characters; // holds an arrayList of characters to add to the game
  private boolean isDragonTurn; // is true if it is the dragon's turn, false otherwise
  private int gameState; // if the value is 0 the game should continue, if the value is 1 that means
  // the player won, and if the value is 2 the player lost.
  
  /**
   * Main method for the DragonTreasureGame class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) 
  {
    PApplet.main("DragonTreasureGame");
  }
  
  /**
   * This method sets the window length and width that will be used for the game.
   */
  @Override
  public void settings()
  {
    size(800,600);
  }
  
  /**
   * This method sets up the window that will be used for the game.
   */
  @Override
  public void setup()
  {
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); // sets the font size for the text
    isDragonTurn = false; // initialize isDragonTurn so that the player goes first
    gameState = 0; // this means that the game should continue
    TreasureRoom.setTreasureBackground(loadImage("images/treasure.jpg")); // sets the background of
    // all treasure rooms
    PortalRoom.setPortalImage(loadImage("images/portal.png")); // sets the background of all portal
    // rooms
    roomList = new ArrayList<Room>();
    Room.setProcessing(this); // process DragonTreasureGame, since it is a PApplet object
    roomInfo = new File("roominfo.txt"); // load the roominfo file
    mapInfo = new File("map.txt"); // load the map file
    this.loadRoomInfo(); // loads the room information from the file roominfo.txt
    this.loadMap(); // loads the map information from the file map.txt
    characters = new ArrayList<Character>(); // an empty ArrayList of Character objects
    this.loadCharacters(); // loads the characters
  }
  
  /**
   * This method draws the game to the window.
   */
  @Override
  public void draw()
  {
    // finding the index of player, dragon, and KEYHOLDER in the characters arrayList
    int pIndex = -1; // pIndex stores the index of the player in the arrayList characters
    int dIndex = -1; // dIndex stores the index of the dragon in the arrayList characters
    int kIndex = -1; // kIndex stores the index of the keyholder in the arrayList characters
    for (int i = 0; i < characters.size(); i++)
    {
      if (characters.get(i) instanceof Player)
      {
        characters.get(i).getCurrentRoom().draw(); // draws the player's current room
        pIndex = i; // find the player in characters and store its index
      }
      if (characters.get(i) instanceof Dragon)
      {
        dIndex = i; // find the dragon in characters and store its index
      }
      if (characters.get(i).getLabel().equals("KEYHOLDER"))
      {
        kIndex = i; // find the keyholder in characters and store its index
      }
    }
    
    // printing any required warnings and finding the index of the treasure room
    int tIndex = 0; // tIndex stores the index of the treasure room in the arrayList roomList
    if (((Player)(characters.get(pIndex))).isDragonNearby(((Dragon)characters.get(dIndex))))
    {
      System.out.println(Dragon.getDragonWarning()); // if the dragon is nearby the player, then
      // print the dragon warning
    }
    for (int i = 0; i < roomList.size(); i++)
    {
      if (roomList.get(i) instanceof TreasureRoom)
      {
        tIndex = i; // find the treasure room and store its index
        if (((Player)characters.get(pIndex)).isTreasureNearby()) // if the treasure is nearby the
          // player, then print the treasure warning
        {
          System.out.println(TreasureRoom.getTreasureWarning());
        }
      }
      if (roomList.get(i) instanceof PortalRoom)
      {
        if (((Player)characters.get(pIndex)).isPortalNearby())
        {
          System.out.println(PortalRoom.getPortalWarning()); // if the portal room is nearby the
          // player, then print the portal warning
        }
      }
    }
    
    // Letting the player obtain the key
    if (characters.get(kIndex).getCurrentRoom().equals(characters.get(pIndex).getCurrentRoom()))
    {
      ((Player)characters.get(pIndex)).obtainKey(); // since we know that a Player object is at index
      // pIndex, we can cast the object at pIndex to a player in order to use the obtainKey method
      System.out.println("KEY OBTAINED");
    }
    
    // Teleporting the player if they need to teleport
    if (((Player)characters.get(pIndex)).teleport()) // since we know that a Player object is at index
      // pIndex, we can cast the object at pIndex to a player in order to use the teleport method
    {
      System.out.println(PortalRoom.getTeleportMessage()); // print the teleport message
    }
    
    // changing room if it is the dragon's turn and the game should continue
    if (isDragonTurn && gameState == 0)
    {
      if (((Dragon)characters.get(dIndex)).changeRoom(((Dragon)characters.get(dIndex)).pickRoom()))
      // since we know that a Dragon object is at index dIndex, we can cast the object at dIndex to
      // a Dragon in order to use the changeRoom and pickRoom methods
      {
        isDragonTurn = false;
      }
    }
    
    // Checking and updating the gameState field
    if (characters.get(pIndex).getCurrentRoom().equals(roomList.get(tIndex)) 
        && ((Player)characters.get(pIndex)).hasKey())
    {
      gameState = 1; // if the player is in the treasure room and have the key, they win
      System.out.println("You win!");
    }
    if (characters.get(pIndex).getCurrentRoom().equals(characters.get(dIndex).getCurrentRoom()))
    {
      gameState = 2; // if the player is in the same room as the dragon, they lose
      System.out.println("You lose.");
    } 
  }
  
  /**
   * This method takes in numeral keys pressed and utilizes them to switch the player's room
   */
  @Override
  public void keyPressed()
  {
    if (gameState == 0)
    {
      for (int i = 0; i < roomList.size(); i++)
      {
        if (roomList.get(i).getID() == Integer.parseInt(String.valueOf(key)))
        {
          for (int j = 0; j < characters.size(); j++)
          {
            if (characters.get(j) instanceof Player)
            {
              if (((Player)characters.get(j)).changeRoom(roomList.get(i))) // if the key pressed
                // parsed to an integer matches a room in the roomList arrayList, move the player to 
                // that room
              {
                isDragonTurn = true; // if the change was successful, make it the dragon's turn
              }
              else
              {
                System.out.println("Please pick a vald room.");
              }
            }
          }
        }
      }
    }
  }
  
  /** Loads in room info using the file stored in roomInfo
   *  @author Michelle 
   */
  private void loadRoomInfo() 
  {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try 
    {
      
      //scanner to read from file
      fileReader= new Scanner(roomInfo);
      
      //read line by line until none left
      while(fileReader.hasNext()) 
      {
        String nextLine = fileReader.nextLine();
        
        //parse info and create new room 
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); //get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;
        
        if(parts.length >= 3) 
        {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);
         
        }
        
        if(parts.length == 4) 
        {
          description = parts[3].trim(); //get the room description
        }
   
        switch(parts[0].trim()) 
        {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }  
        
        if(newRoom != null) 
        {
          roomList.add(newRoom);
        }
      }
    }catch(IOException e) { //handle checked exception
      e.printStackTrace();
    }finally {
      if(fileReader != null)
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
    }
  }
  
  /** Loads in room connections using the file stored in mapInfo
   *  @author Michelle 
   */
  private void loadMap() 
  {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try 
    {
    //scanner to read from file
      fileReader = new Scanner(mapInfo);
      
    //read line by line until none left
      while(fileReader.hasNext()) 
      {
        //parse info
        String nextLine = fileReader.nextLine();
        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);
        
        Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms
        
        //add all the rooms to the adj room list of toEdit
        for(int i=1; i<parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    }catch(IOException e) { //handle checked exception
      e.printStackTrace();
    }finally {    //close scanner regardless of what happened for security reasons :)
      if(fileReader != null)
        fileReader.close();
    }
  }
  
  /**
   * Get the room objected associated with the given ID.
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id)
  {
    int indexToEdit = roomList.indexOf(new Room(id,"dummy", null));
    Room toEdit = roomList.get(indexToEdit); 
    return toEdit;
  }
  
  private void loadCharacters() 
  {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5),"KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }
}
