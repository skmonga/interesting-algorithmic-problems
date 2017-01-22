package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class W23GravityTree {

	static class TreeNode {
		int index;
		int height;
		boolean found = false;
		TreeNode parent;
		List<Integer> children = new ArrayList<>();
		
		public TreeNode(int index) {
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		TreeNode[] nodes = new TreeNode[n+1];
		nodes[1] = new TreeNode(1);
		String[] arr = br.readLine().split(" ");
		int next_index = 2;
		for(int i = 0; i < n-1; i++) {
			int parent_index = Integer.parseInt(arr[i]);
			if(nodes[next_index] == null)
			    nodes[next_index] = new TreeNode(next_index);
			if(nodes[parent_index] == null)
				nodes[parent_index] = new TreeNode(parent_index);
			nodes[parent_index].children.add(next_index);
			next_index++;
		}
		assignHeightsParentsToNodes(nodes,null,1,0);
		int q = Integer.parseInt(br.readLine());
		for(int i = 1; i <= q; i++) {
			arr = br.readLine().split(" ");
			int u = Integer.parseInt(arr[0]);
			int v = Integer.parseInt(arr[1]);
			System.out.println(findForce(nodes,u,v));
		}
		
	}

	private static void assignHeightsParentsToNodes(TreeNode[] nodes, TreeNode parent,int index, int height) {
		nodes[index].height = height;
		nodes[index].parent = parent;
		for(Integer child : nodes[index].children) {
			assignHeightsParentsToNodes(nodes, nodes[index],child, height + 1);
		}
	}

	private static int findForce(TreeNode[] nodes, int u, int v) {
		int height_u = nodes[u].height;
		int height_v = nodes[v].height;
		TreeNode uNode = nodes[u],vNode = nodes[v];
		int height_gap = Math.abs(height_u - height_v);
		int dist_u_v = height_gap;
		if(height_gap != 0) {
			if(height_u > height_v) {
				while(height_gap != 0) {
					uNode = uNode.parent;
					height_gap--;
				}
			} else {
				while(height_gap != 0) {
					vNode = vNode.parent;
					height_gap--;
				}
			}
		}
		//now height_gap is 0
		boolean checkIfChild = (height_u > height_v && uNode == vNode);
		while(uNode != vNode) {
			uNode = uNode.parent;
			vNode = vNode.parent;
			dist_u_v += 2;
		}
		if(!checkIfChild) {
			return calculateDistance(nodes,u,v,dist_u_v);
		}
		return findForceUtil(nodes,u,v,dist_u_v);
	}

	private static int calculateDistance(TreeNode[] nodes, int u, int v, int dist_u_v) {
		int dist = (int) Math.pow(dist_u_v, 2);
		List<Integer> children = nodes[v].children;
		for(Integer child : children) {
			dist += calculateDistance(nodes, u, child, dist_u_v + 1);
		}
		return dist;
	}

	private static int findForceUtil(TreeNode[] nodes, int u, int v, int dist_u_v) {
		int dist = (int) Math.pow(dist_u_v, 2);
		populateFound(nodes,u,v);
		List<Integer> children = nodes[v].children;
		for(Integer child : children) {
			if(!nodes[child].found) {
				dist += calculateDistance(nodes, u, child, nodes[u].height - nodes[v].height + 1);
			} else {
				dist += calculateDistanceInFound(nodes,u,child);
			}
		}
		return dist;
	}

	private static int calculateDistanceInFound(TreeNode[] nodes, int u, int v) {
		int dist  = (int) Math.pow(nodes[u].height - nodes[v].height, 2);
		List<Integer> children = nodes[v].children;
		for(Integer child : children) {
			if(!nodes[child].found) {
				dist += calculateDistance(nodes, u, child, nodes[u].height - nodes[v].height + 1);
			} else {
				dist += calculateDistanceInFound(nodes, u, child);
			}
		}
		nodes[v].found = false;
		return dist;
	}

	private static boolean populateFound(TreeNode[] nodes, int u, int v) {
		List<Integer> children = nodes[v].children;
		for(Integer child : children) {
			if(child == u) {
				nodes[child].found = true;
			} else {
			populateFound(nodes, u, child);
			}
			nodes[v].found |= nodes[child].found;
		}
		return nodes[v].found;
	}
}
