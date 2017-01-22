package code.practice;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {

	public static void main(String[] args) {
		String str = ")(()))))";
		List<Character> list;
		int index = 0,max_valid = 0;
		while(true) {
			int current_max = 0;
			list = new ArrayList<>();
		while(index < str.length()) {
			if(str.charAt(index) == '(') {
				list.add('(');
			} else {
				if(!list.isEmpty()) {
					list.remove(list.size() - 1);
					current_max += 2;
				} else {
					if(current_max > max_valid)
						max_valid = current_max;
					index++;
					break;
				}
			}
			index++;
		}
		if(current_max > max_valid)
			max_valid = current_max;
		if(index == str.length())
			break;
		}
		System.out.println(max_valid);
	}
}
