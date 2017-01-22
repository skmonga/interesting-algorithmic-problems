package code.practice;

/**
 * Remove all nodes which don’t lie in any path with sum>= k Given a binary
 * tree, a complete path is defined as a path from root to a leaf. The sum of
 * all nodes on that path is defined as the sum of that path. Given a number K,
 * you have to remove (prune the tree) all nodes which don’t lie in any path
 * with sum>=k.
 * 
 * Note: A node can be part of multiple paths. So we have to delete it only in
 * case when all paths from it have sum less than K.
 *
 */
public class PruneTreeOnSum {

	private static Node pruneTreeUtil(Node root, int sum) {
		if(root == null)
			return null;
		
		if(sum <= root.key)       //sum can be achieved with this key
			return root;
		
		root.left = pruneTreeUtil(root.left, sum - root.key);
		root.right = pruneTreeUtil(root.right, sum - root.key);
		
		if(root.left == null && root.right == null) {
			//sum not achieved in both left and right subtrees
			return null;
		}
		
		return root;
	}
	
	private static void PruneTree(BinaryTree tree, int sum) {
		tree.root = pruneTreeUtil(tree.root,sum);
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.left.left.left = new Node(8);
		tree.root.left.right.left = new Node(12);
		tree.root.left.left.right = new Node(9);
		tree.root.left.left.right.left = new Node(13);
		tree.root.left.left.right.right = new Node(14);
		tree.root.left.left.right.right.left = new Node(15);
		tree.root.right = new Node(3);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);
		tree.root.right.right.left = new Node(10);
		tree.root.right.right.left.right = new Node(11);
		tree.printPreorder();
		System.out.println();
		System.out.println("After pruning");
		System.out.println();
		
		int sum = 45;
		PruneTree(tree,sum);
		tree.printPreorder();
		
	}

	
}
