package code.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A complete binary tree is a binary tree whose all levels except the last
 * level are completely filled and all the leaves in the last level are all to
 * the left side.
 *
 */
public class CompleteTree {

	
	private static boolean isCompleteTree(BinaryTree tree) {
		boolean isComp = true;
		Queue<Node> queue = new LinkedList<Node>();
		if(tree.root == null)
			return isComp;
		
		queue.add(tree.root);
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(node.left != null) {
				if(!isComp)
					return false;
				else
					queue.add(node.left);
			} else {
				isComp = false;
			}
			
			if(node.right != null) {
				if(!isComp)
					return false;
				else
					queue.add(node.right);
			} else {
				isComp = false;
			}	
		}
		//reached here means its a complete tree
		return true;
	}
	
	public static void main(String[] args) {
		BinaryTree tree1 = new BinaryTree();
		tree1.root = new Node(1);
		tree1.root.left = new Node(2);
		tree1.root.left.left = new Node(4);
		tree1.root.left.right = new Node(5);
		tree1.root.right = new Node(3);
		System.out.println(isCompleteTree(tree1));
		
		BinaryTree tree2 = new BinaryTree();
		tree2.root = new Node(1);
		tree2.root.left = new Node(2);
		tree2.root.left.left = new Node(4);
		System.out.println(isCompleteTree(tree2));
		
		BinaryTree tree3 = new BinaryTree();
		tree3.root = new Node(1);
		tree3.root.left = new Node(2);
		tree3.root.left.left = new Node(4);
		tree3.root.left.right = new Node(5);
		tree3.root.right = new Node(3);
		tree3.root.right.right = new Node(6);
		System.out.println(isCompleteTree(tree3));
	}
	
}
