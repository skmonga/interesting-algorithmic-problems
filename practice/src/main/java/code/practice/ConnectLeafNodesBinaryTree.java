package code.practice;

/**
 * Connect all leaf nodes of a binary tree to make a LL. (Without allocating any
 * extra space for the linked list).
 *
 */
public class ConnectLeafNodesBinaryTree {

	// stores the head of the linked list formed from leaf nodes
	static Node head;
	
	//stores the last node of linked list
	static Node tail;

	private static void connectLeafNodes(Node root) {
		if(root == null)
			return;
		
		connectLeafNodes(root.left);
		connectLeafNodes(root.right);
		if(root.left == null && root.right == null) {
			//this is a leaf ,make it the tail right away after making proper links
			if(head == null) {
				head = root;
			} else {
				tail.next = root;
			}
			tail = root;
		}
	}
	
	private static void printLinkedList(Node head) {
		while(head != null) {
			System.out.print(head.key + "     ");
			head = head.next;
		}
	}
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.left.left = new Node(3);
		tree.root.left.right = new Node(4);
		tree.root.left.right.left = new Node(5);
		tree.root.left.right.left.right = new Node(6);
		tree.root.left.right.right = new Node(7);
		tree.root.right = new Node(8);
		tree.root.right.left = new Node(9);
		tree.root.right.left.left = new Node(10);
		tree.root.right.left.right = new Node(11);
		tree.root.right.right = new Node(12);
		
		connectLeafNodes(tree.root);
		printLinkedList(head);
	}

	
}
