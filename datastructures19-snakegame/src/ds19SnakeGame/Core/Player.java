/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

package ds19SnakeGame.Core;

public class Player {
    protected int playerId;  // player code
    protected String name;   // name of the player
    protected int score;     // score of the player that is defined by the number of apples that he has eaten
    protected Board board;

    public int getPlayerId(){return  playerId;}
    public String getName(){return  name;}
    public int getScore(){return score;}

    public Board getBoard() {
        return board;
    }

    public Player(int playerId, String name, int score, Board board) {
		this.playerId = playerId;
		this.name = name;
		this.score = score;
		this.board = board;
	}
    
	public void setPlayerId(int playerId){this.playerId = playerId;}
    public void setName(String name){this.name = name ;}
    public void setScore(int score){this.score = score;}
    public void setBoard(Board b){
        b.createBoard();
    }

    public int[] move(int id, int die)
    {
        int[] moveResult = new int[5];
        
        moveResult[0] = id + die;   //1st position of moveResult is the final player position
		System.out.printf("\nPlayer %s has rolled a %d and is now on %d", name, die, moveResult[0]);
        
        for (int i = 0; i < board.getSnakes().length; i++)
        {
        	if (board.getSnakes()[i].getHeadId() == moveResult[0])
        	{
        		moveResult[0] = board.getSnakes()[i].getTailId();
        		moveResult[1]++;    //2nd position of moveResult is the number of snakes
        		System.out.printf("\nPlayer %s has stepped on a snake and falls to %d", name, moveResult[0]);
        	}
        }
        
        for (int i = 0; i < board.getLadders().length; i++)
        {
        	if (board.getLadders()[i].getDownStepId() == moveResult[0] && board.getLadders()[i].getBroken() != true)
        	{
        		moveResult[0] = board.getLadders()[i].getUpStepId();
        		moveResult[2]++;    //3rd position of moveResult is the number of ladders
        		board.getLadders()[i].setBroken(true);
        		System.out.printf("\nPlayer %s has found a ladder and goes up to %d", name, moveResult[0]);
        	}
        }
        
        for (int i = 0; i < board.getApples().length; i++)
        {
        	if (board.getApples()[i].getAppleTileId() == moveResult[0])
        	{
        	    score += board.getApples()[i].getPoints();
            	board.getApples()[i].setPoints(0);   //each apple can give its points only once
            	if (board.getApples()[i].getColor() == "Red")
            	{
                	moveResult[3]++;    //4th position of moveResult is the number of red apples eaten
            		System.out.printf("\nPlayer %s has eaten a red apple. His score is now %d", name, score);
            	}
            	else
            	{
            		moveResult[4]++;   //5th position of moveResult is the number of black apples eaten
            		System.out.printf("\nPlayer %s has eaten a black apple. His score is now %d", name, score);
            	}
        	}
        }
        
        return moveResult;
    }

}
