package code.practice;

/**
 * Given a binary tree, we need to find maximum value we can get by subtracting
 * value of node B from value of node A, where A and B are two nodes of the
 * binary tree and A is an ancestor of B. Expected time complexity is O(n).
 *
 */
public class MaxDiffNodeAndAncestor {

	static int max_Difference = Integer.MIN_VALUE;
	
	/*
	 * As we are given a binary tree, there is no relationship between node
	 * values so we need to traverse whole binary tree to get max difference and
	 * we can obtain the result in one traversal only by following below steps :
	 * If we are at leaf node then just return its value because it can’t be
	 * ancestor of any node. Then at each internal node we will try to get
	 * minimum value from left subtree and right subtree and calculate the
	 * difference between node value and this minimum value and according to
	 * that we will update the result. As we are calculating minimum value while
	 * retuning in recurrence we will check all optimal possibilities (checking
	 * node value with minimum subtree value only) of differences and hence
	 * calculate the result in one traversal only.
	 */

	private static int maxDiffBetweenNodeAncestorUtil(Node root) {

	}

	private static void maxDiffBetweenNodeAncestor(BinaryTree tree) {
		if (tree.root == null)
			return;
		System.out.println(maxDiffBetweenNodeAncestorUtil(tree.root));
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

		maxDiffBetweenNodeAncestor(tree);
	}

}
