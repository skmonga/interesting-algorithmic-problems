package code.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *Print root to leaf paths without using recursion
Given a binary tree, print all its root to leaf paths without using recursion. 
For example, consider the following Binary Tree.

        6
     /    \
    3      5
  /   \     \
 2     5     4
     /   \
    7     4

There are 4 leaves, hence 4 root to leaf paths -
  6->3->2              
  6->3->5->7
  6->3->5->4
  6->5>4
 *
 */
public class RootToLeafPaths {

	
	private static void printPathUtil(Node node, Map<Node, Node> parentPointer) {
		if(node == null) 
			return;
		//here node is a leaf node 
		//we have to print from root to node ,so we use a stack again and put parents in it.
		Stack<Node> stack = new Stack<>();
		while(node != null) {
			stack.push(node);
			node = parentPointer.get(node);
		}
		
		//now pop elements from the stack to print in correct order
		while(!stack.isEmpty()) {
			System.out.print(stack.pop().key + "   ");
		}
		System.out.println();
	}
	
	private static void printRootToLeafPaths(Node root) {
		//iterative preorder can be used to traverse tree without recursion
		//but how to print all paths from root to leaf
		//for this keep a map of parent pointer for every node that we visit
		Stack<Node> stack = new Stack<>();
		Map<Node,Node> parentPointer = new HashMap<>();
		//for root ,there is no parent so insert null and push it to stack
		stack.push(root);
		parentPointer.put(root, null);
		Node node;
		
		while(!stack.isEmpty()) {
			node = stack.pop();
			if(node.right != null) {
				stack.push(node.right);
				parentPointer.put(node.right, node);
			}
			if(node.left != null) {
				stack.push(node.left);
				parentPointer.put(node.left, node);
			}
			
			if(node.left == null && node.right == null) {
				printPathUtil(node,parentPointer);
			}
		}
	}
	

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(6);
		tree.root.left = new Node(3);
		tree.root.left.left = new Node(2);
		tree.root.left.right = new Node(5);
		tree.root.left.right.left = new Node(7);
		tree.root.left.right.right = new Node(4);
		tree.root.right = new Node(5);
		tree.root.right.right = new Node(4);
		
		printRootToLeafPaths(tree.root);
	}

	
}
