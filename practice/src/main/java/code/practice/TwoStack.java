package code.practice;

import java.util.LinkedList;
import java.util.Queue;

class StackNode {
	private Integer data;
	private Integer index;
	private StackNode prev;

	public StackNode() {

	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Integer getIndex() {
		return index;
	}

	public StackNode getPrev() {
		return prev;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setPrev(StackNode prev) {
		this.prev = prev;
	}

}

public class TwoStack {

	StackNode stack1, stack2;
	int n;
	Queue<Integer> freeIndexes;

	public TwoStack(int size) {
		this.n = size;
		if (size >= 0) {
			freeIndexes = new LinkedList<>();
			int i = 0;
			while (i < size) {
				freeIndexes.add(i++);
			}
		}
	}

	void push1(int elem) {

		if (freeIndexes.isEmpty())
			throw new StackOverflowError("Overflow");
		Integer index = freeIndexes.poll();
		StackNode newStack1 = new StackNode();
		newStack1.setData(elem);
		newStack1.setIndex(index);

		if (stack1 == null) {
			stack1 = newStack1;
			stack1.setPrev(null);
		} else {
			StackNode oldStack = stack1;
			stack1 = newStack1;
			stack1.setPrev(oldStack);
		}
	}

	void push2(int elem) {

		if (freeIndexes.isEmpty())
			throw new StackOverflowError("Overflow");
		Integer index = freeIndexes.poll();
		StackNode newStack2 = new StackNode();
		newStack2.setData(elem);
		newStack2.setIndex(index);

		if (stack2 == null) {
			stack2 = newStack2;
			stack2.setPrev(null);
		} else {
			StackNode oldStack = stack2;
			stack2 = newStack2;
			stack2.setPrev(oldStack);
		}
	}

	int pop1() {
		if (stack1 == null) {
			return -1; // no element for stack 1
		} else {
			int top_stack1 = stack1.getData();
			freeIndexes.add(stack1.getIndex());
			stack1 = stack1.getPrev();
			return top_stack1;
		}
	}

	int pop2() {
		if (stack2 == null) {
			return -1; // no element for stack 2
		} else {
			int top_stack2 = stack2.getData();
			freeIndexes.add(stack2.getIndex());
			stack2 = stack2.getPrev();
			return top_stack2;
		}
	}
	
	public static void main(String[] args) {
		Queue<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		
	}
}
