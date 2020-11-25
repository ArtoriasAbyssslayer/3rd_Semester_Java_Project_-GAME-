/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/
package ds19SnakeGame.MiniMaxPlayer;
 
import java.util.ArrayList;
import ds19SnakeGame.Core.Board;

public class Node {
	int nodeDepth;
    int[] nodeMove = {0, 0};
    ArrayList<Node> children;
    Node parent;
    Board nodeBoard;
    double nodeEvaluation;

    public Node(Board b) {                   // used only when the node is a root
    	setNodeDepth(0);                     // the root has a depth of 0
        setParent(null);                     // the root has no parents either
        children = new ArrayList<Node>();
        nodeBoard = b;             
        nodeEvaluation = 0;                  // move's evaluation initially zero
    }
    
    public Node(Node parentNode) {                     // constructor to use when making a child node
        setParent(parentNode);                         // set the parent node
        setNodeDepth(parentNode.getNodeDepth() + 1);   // node depth is the parent's depth + 1
        setNodeBoard(parentNode.getNodeBoard());       // inherit the initial board state from the parent node
        this.children = new ArrayList<Node>();         // make a new children array
        parent.setChild(this);                         // store the child in the parent's children array
    }
        
    public int[] getNodeMove() { return nodeMove; }
    
    public int getNodeDepth() { return nodeDepth; }
    
    public double getNodeEvaluation() { return nodeEvaluation; }
    
    public Node getParent() { return parent; }
    
    public ArrayList<Node> getChildren() { return children; }
    
    public Board getNodeBoard() { return nodeBoard; }

    public void setNodeDepth(int n) { nodeDepth =  n; }
    
    public void setParent(Node parent) { this.parent = parent; }
    
    public void setNodeBoard(Board nodeBoard) { this.nodeBoard = nodeBoard; }
    
    public void setChild(Node child) { children.add(child); }
}
