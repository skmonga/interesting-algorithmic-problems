package code.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Node
{
    int key;
    Node left, right,next;
 
    public Node(int item)
    {
        key = item;
        left = right = null;
        next = null;
    }
}
 
class BinaryTree
{
    // Root of Binary Tree
    Node root;
 
    static Integer target_level;
    
    BinaryTree()
    {
        root = null;
    }
 
    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    void printPostorder(Node node)
    {
        if (node == null)
            return;
 
        // first recur on left subtree
        printPostorder(node.left);
 
        // then recur on right subtree
        printPostorder(node.right);
 
        // now deal with the node
        System.out.print(node.key + " ");
    }
 
    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(Node node)
    {
        if (node == null)
            return;
 
        /* first recur on left child */
        printInorder(node.left);
 
        /* then print the data of node */
        System.out.print(node.key + " ");
 
        /* now recur on right child */
        printInorder(node.right);
    }
 
    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(Node node)
    {
        if (node == null)
            return;
 
        /* first print data of node */
        System.out.print(node.key + " ");
 
        /* then recur on left sutree */
        printPreorder(node.left);
 
        /* now recur on right subtree */
        printPreorder(node.right);
    }
 
    // Wrappers over above recursive functions
    void printPostorder()  {     printPostorder(root);  }
    void printInorder()    {     printInorder(root);   }
    void printPreorder()   {     printPreorder(root);  }
    Node createCopy(Node node) {
    	return createCopyUtil(root,node);
    }
    
    void deleteTree() {
    	root = deleteTree(root);
    }
 
    private Node deleteTree(Node node) {
		if(node == null)
			return null;
		
		node.left = deleteTree(node.left);
		node.right = deleteTree(node.right);
		node = null;
		return node;
	}

	private Node createCopyUtil(Node origNode,Node node) {
		if(origNode == null)
			return null;
		
		node = new Node(origNode.key);  //create the root node
		
		//do for left subtree
		node.left = createCopyUtil(origNode.left, node.left);
		
		//do for right subtree
		node.right = createCopyUtil(origNode.right, node.right);
		
		return node;
	}
	
	void printLevelOrder() 
    {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) 
        {
 
            /* poll() removes the present head.
            For more information on poll() visit 
            http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
            Node tempNode = queue.poll();
            System.out.print(tempNode.key + " ");
 
            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
 
            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
	

	public int minDistanceToLeaf(Node root,Node node) {
		if(isLeaf(node))
			return 0;
		Map<Node, Integer> ancestors = new HashMap<>();
		int max_dist = populateAncestors(node,root,ancestors,0);
	//reset all entries in ancestors to max_dist - current value
		Map<Node, Integer> ancestors_height = new HashMap<>();
		calculateHeight(root,ancestors,ancestors_height);
		return 0; //placeholder
	}

	private int calculateHeight(Node root, Map<Node, Integer> ancestors, Map<Node, Integer> ancestors_height) {
		if(isLeaf(root))
			return 0;
	    int lheight = Integer.MAX_VALUE,rheight = Integer.MAX_VALUE;
	    if(root.left != null) {
	    	lheight = calculateHeight(root.left, ancestors, ancestors_height);
	    	if(ancestors.get(root.left) != null) {
	    		ancestors_height.put(root.left, lheight);
	    	}
	    }
	    if(root.right != null) {
	    	rheight = calculateHeight(root.right, ancestors, ancestors_height);
	    	if(ancestors.get(root.right) != null) {
	    		ancestors_height.put(root.right, rheight);
	    	}
	    }
	    	return ((lheight > rheight)?rheight:lheight) + 1;    
	}

	private int populateAncestors(Node node, Node root, Map<Node, Integer> ancestors,int curDist) {
		if(root == null)  { //not in this subtree
			return -1;
		}
		if(node == root) {
			ancestors.put(root,curDist);
			return curDist;
		}
		ancestors.put(root, curDist);
		int dist_left = populateAncestors(node, root.left, ancestors, curDist+1),dist_right;
		
		if(dist_left == -1) {
		dist_right = populateAncestors(node, root.right, ancestors, curDist+1);
		if(dist_right == -1) {
			ancestors.remove(root);
		}
		return -1;
		} else
			return dist_left;
		
	}

	private int minHeight(Node root) {
		if(isLeaf(root))
			return 0;
		int lheight = (root.left != null) ? minHeight(root.left):Integer.MAX_VALUE;
		int rheight = (root.right != null) ? minHeight(root.right):Integer.MAX_VALUE;
		if(lheight == Integer.MAX_VALUE)
			return 1+rheight;
		else if(rheight == Integer.MAX_VALUE)
			return 1+lheight;
		else
			return 1+((lheight > rheight)?rheight:lheight);	
	}

	private boolean isLeaf(Node node) {
		if(node != null && node.left == null && node.right == null)
			return true;
		return false;
	}
	
	public Node lcaBST (Node root,int key1,int key2) {
	   int min,max;
	   if(key1 < key2) {
		   min = key1;
		   max = key2;
	   } else {
		   min = key2;
		   max = key1;
	   }
	   
	   if(root != null) {
		   if(root.key < min)
			   return lcaBST(root.right, key1, key2);
		   else if(root.key > max)
			   return lcaBST(root.left, key1, key2);
	   }
	   return root;
	}
	
	public Node lcaBinaryTree(Node root,int key1,int key2) {
		if(root == null)
			return null;
		if(root.key == key1 || root.key == key2)
			return root;
		Node lca_left = lcaBinaryTree(root.left, key1, key2);
		Node lca_right = lcaBinaryTree(root.right, key1, key2);
		if(lca_left != null && lca_right != null)
			return root;
		if(lca_left == null && lca_right == null)
			return null;
		
		return (lca_left != null)?lca_left:lca_right;
		
	}
	
	private void findInTargetSubTree(Node node,int dist,int k) {
		if(node == null)
			return;
		if(dist == k) {
			System.out.println(node.key);
			return;
		}
		if(dist < k) {
			findInTargetSubTree(node.left,dist + 1,k);
			findInTargetSubTree(node.right,dist + 1,k);
		}
	}
	
	private void printInOtherSubtree(Node node, int dist, int k) {
		if(node == null)
			return;
		if(dist == k) {
			System.out.println(node.key);
			return;
		}
		if(dist < k) {
			printInOtherSubtree(node.left, dist + 1, k);
			printInOtherSubtree(node.right, dist + 1, k);
		}
	}
	
	boolean printKDistance(Node node,Node target,int cur_level,int k) {
		if(node == null)
			return false;
		if(node == target) {
			target_level = cur_level;
			findInTargetSubTree(target.left, 1, k);
			findInTargetSubTree(target.right, 1, k);
			return true;
		}
		boolean in_left = printKDistance(node.left, target, cur_level + 1, k);
		boolean in_right = printKDistance(node.right, target, cur_level + 1, k);
		if(in_left == false && in_right == false)
			return false;
		else {
			
			if(target_level - cur_level == k)
				System.out.println(node.key);
			
			if(in_left != false) {
				//target found in node's left subtree
				printInOtherSubtree(node.right,target_level - cur_level + 1,k);
			} else {
				//target found in node's right subtree
				printInOtherSubtree(node.left,target_level - cur_level + 1,k);
			}
		}
		return true;
	}

	// Driver method
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(20);
        tree.root.left = new Node(8);
        tree.root.right = new Node(22);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(12);
        tree.root.left.right.left = new Node(10);
        tree.root.left.right.right = new Node(14);
        
        tree.printKDistance(tree.root, tree.root.left, 0, 2);
        
    }

}
