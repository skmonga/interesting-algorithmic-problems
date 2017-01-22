package code.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Consider lines of slope -1 passing between nodes. Given a Binary Tree, print
 * all diagonal elements in a binary tree belonging to same line.
 *
 * Refer to :http://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
 */
public class DiagonalTraversal {

	static class QueueNode {
		int hd;   //horizontal distance 
		Node node;  //the actual node
		
		public QueueNode(int hd,Node node) {
			this.hd = hd;
			this.node = node;
		}
	}
	
	private static void printDiagonalTraversal(Node root) {
		if(root == null)
			return;
		Map<Integer, LinkedList<Node>> nodes = new HashMap<Integer,LinkedList<Node>>();
		Queue<QueueNode> q = new LinkedList<QueueNode>();
		//entering the root
		QueueNode qNode = new QueueNode(0, root);
		q.add(qNode);
		int cur_vd = 0;      //current vertical distance
		int cur_level_nodes = 1,next_level_nodes = 0,diff;
		QueueNode qNode_left,qNode_right;
		while(!q.isEmpty()) {
			qNode = q.poll();
			cur_level_nodes -= 1;
			//calculate difference of vertical and horizontal distance for this node
			diff = cur_vd - qNode.hd;
			if(nodes.get(diff) == null) {
				LinkedList<Node> nodeList = new LinkedList<>();
				nodeList.add(qNode.node);
				nodes.put(diff, nodeList);
			} else {
				LinkedList<Node> nodeList = nodes.get(diff);
				nodeList.add(qNode.node);
			}
			
			if(qNode.node.left != null) {
				qNode_left = new QueueNode(qNode.hd - 1, qNode.node.left);
				q.add(qNode_left);
				next_level_nodes += 1;
			}
			
			if(qNode.node.right != null) {
				qNode_right = new QueueNode(qNode.hd + 1, qNode.node.right);
				q.add(qNode_right);
				next_level_nodes += 1;
			}
			
			if(cur_level_nodes == 0) {
				cur_level_nodes = next_level_nodes;
				next_level_nodes = 0;
				cur_vd += 1;
			}
			
		}
		
		printDiagonalTraversalUtil(nodes);
	}
	
	private static void printDiagonalTraversalUtil(Map<Integer, LinkedList<Node>> nodes) {
		int min_diff = 0;
		LinkedList<Node> nodeList;
		Iterator<Node> it;
		while(nodes.get(min_diff) != null) {
		    nodeList = nodes.get(min_diff);
			it = nodeList.iterator();
			while(it.hasNext()) {
				System.out.print(it.next().key + "    ");
			}
			System.out.println();
			min_diff += 2;
		}
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(8);
		tree.root.left = new Node(3);
		tree.root.left.left = new Node(1);
		tree.root.left.right = new Node(6);
		tree.root.left.right.left = new Node(4);
		tree.root.left.right.right = new Node(7);
		tree.root.right = new Node(10);
		tree.root.right.right = new Node(14);
		tree.root.right.right.left = new Node(13);
		
		printDiagonalTraversal(tree.root);
	}

	
	
}
