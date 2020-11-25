/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

package ds19SnakeGame.Core;

public class Ladder 
{
    private int ladderId;       //the ladder's unique ID
    private int upStepId;       //ID of the tile the top of the ladder is on
    private int downStepId;     //ID of the tile the bottom of the ladder is on

    private boolean broken;     //whether the ladder is broken or not
    
    //Constructors
    public Ladder()    //without args
    {
    	//some random values in order to have something initialized
    	setLadderId(0);
    	setUpStepId(0);
    	setDownStepId(0);
    	setBroken(true);
    }
    
    public Ladder(int ladderId, int upStepId, int downStepId, boolean broken)  //with args
    {
    	setLadderId(ladderId);
    	setUpStepId(upStepId);
    	setDownStepId(downStepId);
    	setBroken(broken);
    }
    
    public Ladder(Ladder ladder)  //copy constructor
    {
    	setLadderId(ladder.getLadderId());
    	setUpStepId(ladder.getUpStepId());
    	setDownStepId(ladder.getDownStepId());
    	setBroken(ladder.getBroken());
    }
    
    /*
     * Helper functions (getters, setters etc.)
     */
    //Getters
    public int getLadderId()
    {
    	return ladderId;
    }
    
    public int getUpStepId()
    {
    	return upStepId;
    }
    
    public int getDownStepId()
    {
    	return downStepId;
    }
    
    public boolean getBroken()
    {
    	return broken;
    }
    
    //Setters
    public void setLadderId(int ladderId)
    {
    	this.ladderId = ladderId;
    }
    
    public void setUpStepId(int upStepId)
    {
    	this.upStepId = upStepId;
    }
    
    public void setDownStepId(int downStepId)
    {
    	this.downStepId = downStepId;
    }
    
    public void setBroken(boolean broken)
    {
    	this.broken = broken;
    }
}