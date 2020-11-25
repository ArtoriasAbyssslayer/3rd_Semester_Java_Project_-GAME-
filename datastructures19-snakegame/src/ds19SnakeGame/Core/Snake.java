/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

package ds19SnakeGame.Core;

public class Snake 
{
    private int snakeId;       //the snake's unique ID
    private int headId;        //ID of the tile the snake's head is on
    private int tailId;        //ID of the tile the snake's tail is on
	
    //Constructors
    public Snake() //without args
    {
		setSnakeId(0);
		setHeadId(0);
		setTailId(0);
	}
    
    public Snake(int snakeId, int headId, int tailId) //with args
    {
		setSnakeId(snakeId);
		setHeadId(headId);
		setTailId(tailId);
	}
    
    public Snake(Snake snake) //copy constructor
    {
		setSnakeId(snake.getSnakeId());
		setHeadId(snake.getHeadId());
		setTailId(snake.getTailId());
	}

    /*
     * Helper functions (getters, setters etc.)
     */
    //Getters
	public int getSnakeId() 
	{
		return snakeId;
	}

	public int getHeadId() 
	{
		return headId;
	}

	public int getTailId()
    {
		return tailId;
	}

	//Setters
	public void setSnakeId(int snakeId) 
	{
		this.snakeId = snakeId;
	}

	public void setHeadId(int headId) 
	{
		this.headId = headId;
	}

	public void setTailId(int tailId) 
	{
		this.tailId = tailId;
	}
}