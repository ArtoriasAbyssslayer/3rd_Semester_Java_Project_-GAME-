/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

package ds19SnakeGame.Core;

public class Apple 
{
    private int appleId;       //the apple's unique ID
    private int appleTileId;   //ID of the tile the apple is on
    private String color;      //the apple's color (red or black)
    private int points;        //apple's score (positive for red, negative for black)
    
    //Constructors
    public Apple()    //without args
    {
    	//some random values in order to have something initialized
    	setAppleId(0);
    	setAppleTileId(0);
    	setColor("Red");
    	setPoints(0);
    }
    
    public Apple(int appleId, int appleTileId, int points, String color)  //with args
    {
    	setAppleId(appleId);
    	setAppleTileId(appleTileId);
    	setPoints(points);
    	setColor(color);
    }
    
    public Apple(Apple apple)  //copy constructor
    {
    	setAppleId(apple.getAppleId());
    	setAppleTileId(apple.getAppleTileId());
    	setColor(apple.getColor());
    	setPoints(apple.getPoints());
    }
    
    /*
     * Helper functions (getters, setters etc.)
     */
    //Getters
    public int getAppleId()
    {
    	return appleId;
    }
    
    public int getAppleTileId()
    {
    	return appleTileId;
    }
    
    public int getPoints()
    {
    	return points;
    }
    
    public String getColor()
    {
    	return color;
    }
    
    //Setters
    public void setAppleId(int appleId)
    {
    	this.appleId = appleId;
    }
    
    public void setAppleTileId(int appleTileId)
    {
    	this.appleTileId = appleTileId;
    }
    
    public void setPoints(int points)
    {
    	this.points = points;
    }
    
    public void setColor(String color)
    {
    	this.color = color;
    }
}