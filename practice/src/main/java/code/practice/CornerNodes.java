package code.practice;

import java.util.LinkedList;
import java.util.Queue;

public class CornerNodes {

	
	private static void printCornerNodes(BinaryTree tree) {
		
		Queue<Node> queue = new LinkedList<Node>();
        queue.add(tree.root);
        /*
         * two variables ,cur_level_nodes and next_level_nodes
         * cur_level_nodes - no. of nodes in current level being traversed
         * next_level_nodes - no.of nodes in next level
         * while traversing a level, cur_level_nodes decrease and next_level_nodes increase
         * when cur_level_nodes is 0, then make cur_level_nodes = next_level_nodes and next_level_nodes to 0.
         */
        int cur_level_nodes = 1,next_level_nodes = 0,level_length = 1;
        
        while (!queue.isEmpty()) 
        {
            Node tempNode = queue.poll();
            
            //print this node's key if it is leftmost or rightmost
            //leftmost if cur_level_nodes == level_length
            //rightmost if cur_level_nodes == 1
            if(cur_level_nodes == level_length || cur_level_nodes == 1) {
            System.out.print(tempNode.key + " ");
            }
 
            cur_level_nodes -= 1;
            
            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
                next_level_nodes += 1;
            }
 
            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
                next_level_nodes += 1;
            }
            
            //if this is last node on a level
            if(cur_level_nodes == 0) {
            	cur_level_nodes = next_level_nodes;
            	level_length = next_level_nodes;
            	next_level_nodes = 0;
            }
        }

	}
	
	// Driver method
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(15);
		tree.root.left = new Node(10);
		tree.root.left.left = new Node(8);
		tree.root.left.right = new Node(12);
		tree.root.right = new Node(20);
		tree.root.right.left = new Node(16);
		tree.root.right.right = new Node(25);
		
		CornerNodes.printCornerNodes(tree);
	}
}
