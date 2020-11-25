/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

package ds19SnakeGame.HeuristicPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ds19SnakeGame.Core.Player;
import ds19SnakeGame.Core.Board;

public class HeuristicPlayer extends Player
{
    protected ArrayList<Integer[]> path;

    public HeuristicPlayer(int playerId, String name, int score, Board board)
    {
        super(playerId, name, score, board);
        path = new ArrayList<Integer[]>(6);
    }
    
    public ArrayList<Integer[]> getPath()
    {
    	return path;
    }
    
    public double evaluate(int currentPos, int dice)
    {
    	int distance = dice;
    	int score = 0;
    	
    	// Distance evaluation.
    	// TODO: Make it so it doesn't search for snake heads if there's a ladder base found at a particular ID
    	for (int i = 0; i < board.getLadders().length; i++)
    	{
    		if ((board.getLadders()[i].getDownStepId() == currentPos + dice) && 
    				(board.getLadders()[i].getBroken() == false))
    		{
    			distance = board.getLadders()[i].getUpStepId() - currentPos;
    			break;
    		}
    	}
    	
    	for (int i = 0; i < board.getSnakes().length; i++)
    	{
    		if ((board.getSnakes()[i].getHeadId() == currentPos + dice))
    		{
    			distance = -(currentPos - board.getSnakes()[i].getTailId());
    			break;
    		}
    	}
    	
    	// Score evaluation
    	for (int i = 0; i < board.getApples().length; i++)
    	{
    		if (board.getApples()[i].getAppleTileId() == currentPos + dice)
    		{
    			score += board.getApples()[i].getPoints();
    		}
    	}
    	
    	return 0.65 * distance + 0.35 * score;
    }
    
    public int getNextMove(int currentPos) {
    	// create a map keeping all possible dice as keys and evaluations as values
        Map<Integer, Double> possibleMoves = new HashMap<Integer, Double>();
    	// current maximum evaluation for a given die
        double currEval = 0;
        // die needed for the current best move
        int neededDie = 0;
        
    	for (int i = 0; i < 6; i++) {
            // evaluate all possible moves according to the target function
    		double eval = evaluate(currentPos, i + 1);
            possibleMoves.put(i + 1, eval);
        }
        
        for (int i = 0; i < 6; i++) {
        	// find die number needed for the best move 
        	if (possibleMoves.get(i + 1) > currEval) {
        		currEval = possibleMoves.get(i + 1);
        		neededDie = i + 1;
        	}
        }
        
        // get the player status after doing the chosen move
        int[] statusAfterMove = move(currentPos, neededDie);
        
        /*
         * Path move array (pathReg) format:
         * index 0: needed number on die for the best move
         * index 1: total score of the move
         * ((black apples * -5) + (red apples * 5), -5 and 5 must change when the apple scores change)
         * index 2: distance traveled (id_after - current position)
         * index 3: total number of apples for this move
         * index 4: total number of snakes for this move
         * index 5: total number of ladders for this move
         */
        Integer[] pathReg = {neededDie, (statusAfterMove[4] * 5) + (statusAfterMove[4] * (-5)), 
        		(statusAfterMove[0] - currentPos), (statusAfterMove[3] + statusAfterMove[4]), 
        		statusAfterMove[1], statusAfterMove[2]};
        
        // store the chosen move to the path
        path.add(pathReg);
        
        // return the player's new position
        return statusAfterMove[0];
   }
    
    public void statistics(int currentRound) {
    	Integer[] lastMove = path.get(path.size() - 1);
    	int allSnakes = 0, allLadders = 0, allReds = 0, allBlacks = 0;
        System.out.println("\n\nThe player is at round: " + currentRound);
        System.out.println("The player's dice roll is :" + lastMove[0]);
        
        if(lastMove[5] > 0) {
            System.out.println("The player landed into a ladder base and climbed it");
        }
        
        for (int i = 0; i < path.size(); i++) {
        	allSnakes += path.get(i)[4];
        	allLadders += path.get(i)[5];
        	allReds += (path.get(i)[1] + 5 * path.get(i)[3]) / 10;
        	allBlacks += (5 * path.get(i)[3] - path.get(i)[1]) / 10;
        }
        
        System.out.println("The number of snake heads that player has stepped into is:" + allSnakes);
        System.out.println("The number of ladder bottoms that player has stepped into is:" + allLadders);
        
        // the following relations are given by solving this 2x2 system:
        /*
         * -5x + 5y = pathReg[1]
         *   x + y  = pathReg[3]
         *   (for x = black apples, y = red apples, we have): 
         *   x = (5 * pathReg[3] - pathReg[1]) / 10
         *   y = (pathReg[1] + 5 * pathReg[3]) / 10
         */
        System.out.println("The number of red apples that the player has eaten until now is:" + allReds);
        System.out.println("The number of black apples that the player has eaten until now is:" + allBlacks);
    }

}
