package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerrank.com/challenges/merging-communities
 *
 */
public class MergingCommunities {

	static class Member {
		int index;
		int parent;
		int rank;
		
		public Member(int i,int p,int r) {
			this.index = i;
			this.parent = p;
			this.rank = r;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int N = Integer.parseInt(arr[0]);
		int Q = Integer.parseInt(arr[1]);
		//the member array ,index from 0 to N
		//the element at index 0 is not used
		Member[] members = new Member[N+1];
		int first,second;
		for(int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			if("M".equals(arr[0])) {
				first = Integer.parseInt(arr[1]);
				second = Integer.parseInt(arr[2]);
				if(members[first] == null) {
					members[first] = new Member(first, first, 1);
				}
				if(members[second] == null) {
					members[second] = new Member(second, second, 1);
				} 
				doUnion(members,first,second);
			} else {
				first = Integer.parseInt(arr[1]);
				if(members[first] == null) {
					members[first] = new Member(first, first, 1);
				}
				System.out.println(members[find(members,first)].rank);
			}
		}
	}

	private static int find(Member[] members, int index) {
		if(members[index].parent != index) {
			members[index].parent = find(members, members[index].parent);
		}
		return members[index].parent;
	}

	private static void doUnion(Member[] members, int first, int second) {
		Member f_root = members[find(members,first)];
		Member s_root = members[find(members,second)];
		if(f_root == s_root)
			return;
		if(f_root.rank < s_root.rank) {
			f_root.parent = s_root.index;
			s_root.rank += f_root.rank;
		} else {
			s_root.parent = f_root.index;
			f_root.rank += s_root.rank;
		}
	}
	
}
