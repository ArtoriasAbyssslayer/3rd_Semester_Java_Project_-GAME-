/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/
package ds19SnakeGame.MiniMaxPlayer;
 
import ds19SnakeGame.Core.Board;
import ds19SnakeGame.HeuristicPlayer.*;
import ds19SnakeGame.MiniMaxPlayer.Node;

public class MiniMaxPlayer extends HeuristicPlayer {  // inherits HeuristicPlayer instead of Player for code recycling
	Node root;
	
	public MiniMaxPlayer(int playerId, String name, int score, Board board) {
		super(playerId, name, score, board);
	}
	
	public int getNextMove(int currentPos, int opponentCurrentPos) {
		root = new Node(board);
		
		createMySubtree(root, currentPos, opponentCurrentPos);
		int bestDie = chooseMinMaxMove(root);
		int[] statusAfterMove = move(currentPos, bestDie);
		
		Integer[] pathReg = {bestDie, (statusAfterMove[4] * 5) + (statusAfterMove[4] * (-5)), 
        		(statusAfterMove[0] - currentPos), (statusAfterMove[3] + statusAfterMove[4]), 
        		statusAfterMove[1], statusAfterMove[2]};
		
		path.add(pathReg);
		
		return statusAfterMove[0];
	}
	
	// These create the tree and populate the leaf nodes with the move evaluations
	private void createMySubtree(Node parent, int currentPos, int opponentCurrentPos) {
		int numOfAvailableMovements;
		
		if (parent.getNodeBoard().getM() * parent.getNodeBoard().getN() - currentPos >= 6) {
			numOfAvailableMovements = 6;
		}
		else {
			numOfAvailableMovements = parent.getNodeBoard().getM() * parent.getNodeBoard().getN() - currentPos;
		}
		
		for (int i = 1; i <= numOfAvailableMovements; i++) {
			Node myChild = new Node(parent);  // the parent's board is automatically inherited by the child node
			myChild.nodeMove[0] = currentPos;
			myChild.nodeMove[1] = i;
			createOpponentSubtree(myChild, currentPos, opponentCurrentPos);
		}
	}
	
	private void createOpponentSubtree(Node parent, int currentPos, int opponentCurrentPos) {
		int numOfAvailableMovements;
		
		if (parent.getNodeBoard().getM() * parent.getNodeBoard().getN() - opponentCurrentPos >= 6) {
			numOfAvailableMovements = 6;
		}
		else {
			numOfAvailableMovements = parent.getNodeBoard().getM() * parent.getNodeBoard().getN() - opponentCurrentPos;
		}
		
		for (int i = 1; i <= numOfAvailableMovements; i++) {
			Node opponentChild = new Node(parent);
			opponentChild.nodeMove[0] = opponentCurrentPos;
			opponentChild.nodeMove[1] = i;
			opponentChild.nodeEvaluation = evaluate(currentPos, i);
		}
	}

	// Minmax implementation
	private int chooseMinMaxMove(Node root) {
		double minEval = Double.POSITIVE_INFINITY;
		double maxEval = Double.NEGATIVE_INFINITY;
		int chosenNodeIndex = 0;
		
		// traverse all leaf nodes, find the minimum eval value and store it to the respective parent
		for (int i = 0; i < root.getChildren().size(); i++) {

			for (int j = 0; j < root.getChildren().get(i).getChildren().size(); j++) {
				if (root.getChildren().get(i).getChildren().get(j).getNodeEvaluation() < minEval) {
					minEval = root.getChildren().get(i).getChildren().get(j).getNodeEvaluation();
				}
			}
			
			root.getChildren().get(i).nodeEvaluation = minEval;  // store the min. eval. to the parent node 
		}
		
		for (int k = 0; k < root.getChildren().size(); k++) {
			
			if (root.getChildren().get(k).nodeEvaluation > maxEval) {
				maxEval = root.getChildren().get(k).getNodeEvaluation();
				chosenNodeIndex = k;
			}
		}
		
		return root.getChildren().get(chosenNodeIndex).getNodeMove()[1];  // return the die resulting in the best move
	}
}
