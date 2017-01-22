package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Refer to : https://www.hackerrank.com/challenges/dijkstrashortreach
 *
 */
public class ShortestReach2 {

	static class AdjNode implements Comparable<AdjNode> {
		int dest;
		int weight;

		public AdjNode(int d, int w) {
			this.dest = d;
			this.weight = w;
		}

		public int getDest() {
			return dest;
		}

		public void setDest(int dest) {
			this.dest = dest;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public int compareTo(AdjNode obj) {
			return (this.weight <= obj.weight) ? -1 : 1;
		}

	}

	static class Graph {

		int V;
		LinkedList<AdjNode> adjList[];

		public Graph(int v) {
			this.V = v;
			adjList = new LinkedList[v];
			for (int i = 0; i < v; i++)
				adjList[i] = new LinkedList<AdjNode>();
		}

		void addEdge(int u, int v, int weight) {
			// nodes are 1-indexed so use by subtracting 1
			adjList[u - 1].add(new AdjNode(v - 1, weight));
			adjList[v - 1].add(new AdjNode(u - 1, weight));
		}
	}

	static class HeapDijkstra {

		protected AdjNode[] array;
		protected int size;

		public AdjNode[] getArray() {
			return array;
		}

		public int getSize() {
			return size;
		}

		/**
		 * Constructs a new BinaryHeap.
		 */
		@SuppressWarnings("unchecked")
		public HeapDijkstra(int size) {
			// Java doesn't allow construction of arrays of placeholder data
			// types
			array = new AdjNode[size];
			this.size = 0;
		}

		/**
		 * Adds a value to the min-heap.
		 */
		public void add(AdjNode value, boolean bubbleUp, int[] indexInHeap) {
			// grow array if needed
			if (size >= array.length) {
				array = this.resize();
			}

			// place element into heap at bottom
			array[size++] = value;

			if (bubbleUp) {
				bubbleUp(indexInHeap);
			}
		}

		/**
		 * Returns true if the heap has no elements; false otherwise.
		 */
		public boolean isEmpty() {
			return size == 0;
		}

		/**
		 * Returns (but does not remove) the minimum element in the heap.
		 */
		public AdjNode peek() {
			if (this.isEmpty()) {
				throw new IllegalStateException();
			}

			return array[0];
		}

		/**
		 * Removes and returns the root element in the heap(minimum if min heap
		 * ,maximum if max heap).
		 */
		public AdjNode remove(int[] indexInHeap) {
			AdjNode result = peek();

			// get rid of the last leaf/decrement
			AdjNode lastNode = getArray()[size - 1];
			indexInHeap[lastNode.getDest()] = 0;
			array[0] = array[size - 1];
			array[size - 1] = null;
			size--;

			bubbleDown(indexInHeap);

			return result;
		}

		/**
		 * Returns a String representation of BinaryHeap with values stored with
		 * heap structure and order properties.
		 */
		public String toString() {
			return Arrays.toString(array);
		}

		/**
		 * Performs the "bubble down" operation to place the element that is at
		 * the root of the heap in its correct place so that the heap maintains
		 * the min-heap order property.
		 */
		protected void bubbleDown(int[] indexInHeap) {
			int index = 0;

			// bubble down
			while (hasLeftChild(index)) {
				// which of my children is smaller?
				int smallerChild = leftIndex(index);

				// bubble with the smaller child, if I have a smaller child
				if (hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
					smallerChild = rightIndex(index);
				}

				if (array[index].compareTo(array[smallerChild]) > 0) {
					swap(index, smallerChild, indexInHeap);
				} else {
					// otherwise, get outta here!
					break;
				}

				// make sure to update loop counter/index of where last el is
				// put
				index = smallerChild;
			}
		}

		/**
		 * Performs the "bubble up" operation to place a newly inserted element
		 * (i.e. the element that is at the size index) in its correct place so
		 * that the heap maintains the min-heap order property.
		 */
		protected void bubbleUp(int[] indexInHeap) {
			int index = this.size - 1;

			while (hasParent(index) && (parent(index).compareTo(array[index]) > 0)) {
				// parent/child are out of order; swap them
				swap(index, parentIndex(index), indexInHeap);
				index = parentIndex(index);
			}
		}

		protected boolean hasParent(int i) {
			return i > 0;
		}

		protected int leftIndex(int i) {
			return i * 2 + 1;
		}

		protected int rightIndex(int i) {
			return i * 2 + 2;
		}

		protected boolean hasLeftChild(int i) {
			return leftIndex(i) < size;
		}

		protected boolean hasRightChild(int i) {
			return rightIndex(i) < size;
		}

		protected AdjNode parent(int i) {
			return array[parentIndex(i)];
		}

		protected int parentIndex(int i) {
			return (i - 1) / 2;
		}

		protected AdjNode[] resize() {
			return Arrays.copyOf(array, array.length * 2);
		}

		protected void swap(int index1, int index2, int[] indexInHeap) {
			AdjNode tmp = array[index1];
			array[index1] = array[index2];
			array[index2] = tmp;
			if (indexInHeap != null) {
				indexInHeap[array[index1].getDest()] = index2;
				indexInHeap[array[index2].getDest()] = index1;
			}
		}

		public void bubbleUpFromIndex(int index, int[] indexInHeap) {
			while (hasParent(index) && (parent(index).compareTo(array[index]) > 0)) {
				// parent/child are out of order; swap them
				swap(index, parentIndex(index), indexInHeap);
				index = parentIndex(index);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		int nodes, edges;
		String[] arr = null;
		Graph graph = null;
		for (int i = 1; i <= T; i++) {
			// this is ith test case
			arr = br.readLine().split(" ");
			nodes = Integer.parseInt(arr[0].trim());
			edges = Integer.parseInt(arr[1].trim());
			graph = new Graph(nodes);
			for (int j = 1; j <= edges; j++) {
				arr = br.readLine().split(" ");
				graph.addEdge(Integer.parseInt(arr[0].trim()), Integer.parseInt(arr[1].trim()),
						Integer.parseInt(arr[2].trim()));
			}
			int startPoint = Integer.parseInt(br.readLine().trim());
			printShortestDistances(startPoint, graph);
		}

	}

	private static void printShortestDistances(int startPoint, Graph graph) {
		int[] dist = new int[graph.V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startPoint - 1] = 0; // self distance
		HeapDijkstra minHeap = new HeapDijkstra(graph.V);
		Iterator<AdjNode> itr = graph.adjList[startPoint - 1].iterator();
		while (itr.hasNext()) {
			AdjNode elem = itr.next();
			dist[elem.dest] = elem.weight;
			minHeap.add(elem, true, null);
		}

		for (int i = 0; i < graph.V; i++) {
			if (i == startPoint - 1) // don't add self to minHeap
				continue;
			if (dist[i] == Integer.MAX_VALUE) // not an adjacent node to source
				minHeap.add(new AdjNode(i, dist[i]), false, null);
		}

		int[] indexInHeap = new int[graph.V];
		indexInHeap[startPoint - 1] = -1; // source is not in heap
		fillIndexesInHeap(indexInHeap, minHeap);

		AdjNode min, adjacent, heapNode;
		while (!minHeap.isEmpty() && minHeap.peek().weight != Integer.MAX_VALUE) {
			min = minHeap.remove(indexInHeap);
			dist[min.dest] = min.weight;
			indexInHeap[min.dest] = -1;
			itr = graph.adjList[min.dest].iterator();
			while (itr.hasNext()) {
				adjacent = itr.next();
				if (indexInHeap[adjacent.dest] != -1 && dist[min.dest] + adjacent.weight < dist[adjacent.dest]) {
					// dist[adjacent.dest] = dist[min.dest] + adjacent.weight;
					heapNode = minHeap.getArray()[indexInHeap[adjacent.dest]];
					heapNode.weight = dist[min.dest] + adjacent.weight;
					dist[adjacent.dest] = heapNode.weight;
					minHeap.bubbleUpFromIndex(indexInHeap[adjacent.dest], indexInHeap);
				}
			}
		}

		for (int i = 0; i < graph.V; i++) {
			if (i == startPoint - 1)
				continue;
			if (dist[i] == Integer.MAX_VALUE)
				dist[i] = -1;
			System.out.print(dist[i] + " ");
		}
		System.out.println();
	}

	private static void fillIndexesInHeap(int[] indexInHeap, HeapDijkstra minHeap) {
		AdjNode[] heapNodes = minHeap.getArray();
		for (int i = 0; i < minHeap.getSize(); i++)
			indexInHeap[heapNodes[i].dest] = i;
	}
}
