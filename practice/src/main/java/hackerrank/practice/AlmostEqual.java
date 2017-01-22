package hackerrank.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Refer to : https://www.hackerrank.com/challenges/almost-equal-advanced
 * @author kurlak 
 * This is the best solution for java language
 *
 */
public class AlmostEqual {
	
    public static void main(String[] args) throws IOException {
        // Read user input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        final int numWrestlers = Integer.parseInt(parts[0]);
        int maxHeightDifference = Integer.parseInt(parts[1]);
        String[] heightStrings = br.readLine().split(" ");
        int[] heights = new int[numWrestlers];
        Wrestler[] wrestlers = new Wrestler[numWrestlers];

        if (numWrestlers == 0) {
            System.out.println("0");
            return;
        }

        for (int i = 0; i < numWrestlers; ++i) {
            heights[i] = Integer.parseInt(heightStrings[i]);
            int height = Integer.parseInt(heightStrings[i]);
            wrestlers[i] = new Wrestler(height, i);
        }

        int numQueries = Integer.parseInt(br.readLine());
        Query[] queries = new Query[numQueries];

        for (int i = 0; i < numQueries; ++i) {
            String[] queryStrings = br.readLine().split(" ");
            int queryLow = Integer.parseInt(queryStrings[0]);
            int queryHigh = Integer.parseInt(queryStrings[1]);
            queries[i] = new Query(queryLow, queryHigh, i);
        }

        // Sort wrestlers by height and make a reverse map

        Wrestler[] sortedWrestlers = new Wrestler[numWrestlers];
        System.arraycopy(wrestlers, 0, sortedWrestlers, 0, numWrestlers);

        Arrays.sort(sortedWrestlers, new Comparator<Wrestler>() {
            public int compare(Wrestler wrestler1, Wrestler wrestler2) {
                return wrestler1.height - wrestler2.height;
            }
        });

        Map<Integer, SortedTriplet> unsortedIndexToSortedTriplet = new HashMap<Integer, SortedTriplet>();

        for (int i = 0; i < numWrestlers; ++i) {
            Wrestler currentWrestler = sortedWrestlers[i];

            int unsortedIndex = currentWrestler.index;
            int heightIndex = i;
            int heightMinusKIndex = findLeftmostValueGTE(sortedWrestlers, currentWrestler.height - maxHeightDifference);
            int heightPlusKIndex = findRightmostValueLTE(sortedWrestlers, currentWrestler.height + maxHeightDifference);
            SortedTriplet sortedTriplet = new SortedTriplet(heightIndex, heightMinusKIndex, heightPlusKIndex);

            unsortedIndexToSortedTriplet.put(unsortedIndex, sortedTriplet);
        }


        // Sort queries according to Mo's algorithm

        Arrays.sort(queries, new Comparator<Query>() {
            public int compare(Query query1, Query query2) {
                int sqrtMaxHigh = (int)Math.sqrt(numWrestlers);

                int sqrtBucket1 = (query1.low / sqrtMaxHigh);
                int sqrtBucket2 = (query2.low / sqrtMaxHigh);

                if (sqrtBucket1 == sqrtBucket2) {
                    return query1.high - query2.high;
                }

                return sqrtBucket1 - sqrtBucket2;
            }
        });

        // Process queries in sorted order

        int currentLow = 0;
        int currentHigh = -1;
        long currentAnswer = 0;
        long[] answers = new long[numQueries];
        FenwickTree wrestlersInQuery = new FenwickTree(numWrestlers);

        for (int i = 0; i < numQueries; ++i) {
            Query query = queries[i];

            while (currentLow < query.low) {
                SortedTriplet sorted = unsortedIndexToSortedTriplet.get(currentLow);
                int numWrestlersInRangeOfValue = wrestlersInQuery.sum(sorted.heightMinusKIndex, sorted.heightPlusKIndex) - 1;
                currentAnswer -= numWrestlersInRangeOfValue;
                wrestlersInQuery.add(sorted.heightIndex, -1);
                ++currentLow;
            }

            while (currentLow > query.low) {
                --currentLow;
                SortedTriplet sorted = unsortedIndexToSortedTriplet.get(currentLow);
                int numWrestlersInRangeOfValue = wrestlersInQuery.sum(sorted.heightMinusKIndex, sorted.heightPlusKIndex);
                currentAnswer += numWrestlersInRangeOfValue;
                wrestlersInQuery.add(sorted.heightIndex, 1);
            }

            while (currentHigh < query.high) {
                ++currentHigh;
                SortedTriplet sorted = unsortedIndexToSortedTriplet.get(currentHigh);
                int numWrestlersInRangeOfValue = wrestlersInQuery.sum(sorted.heightMinusKIndex, sorted.heightPlusKIndex);
                currentAnswer += numWrestlersInRangeOfValue;
                wrestlersInQuery.add(sorted.heightIndex, 1);
            }

            while (currentHigh > query.high) {
                SortedTriplet sorted = unsortedIndexToSortedTriplet.get(currentHigh);
                int numWrestlersInRangeOfValue = wrestlersInQuery.sum(sorted.heightMinusKIndex, sorted.heightPlusKIndex) - 1;
                currentAnswer -= numWrestlersInRangeOfValue;
                wrestlersInQuery.add(sorted.heightIndex, -1);
                --currentHigh;
            }

            answers[query.index] = currentAnswer;
        }

        // Output results

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numQueries; ++i) {
            result.append(answers[i]);
            result.append("\n");
        }

        System.out.print(result.toString());
    }

    private static int findLeftmostValueGTE(Wrestler[] sortedWrestlers, int valueToFind) {
        int n = sortedWrestlers.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (sortedWrestlers[mid].height >= valueToFind && (mid == 0 || sortedWrestlers[mid - 1].height < valueToFind)) {
                return mid;
            } else if (sortedWrestlers[mid].height < valueToFind) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private static int findRightmostValueLTE(Wrestler[] sortedWrestlers, int valueToFind) {
        int n = sortedWrestlers.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (sortedWrestlers[mid].height <= valueToFind && (mid == n - 1 || sortedWrestlers[mid + 1].height > valueToFind)) {
                return mid;
            } else if (sortedWrestlers[mid].height > valueToFind) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    private static class SortedTriplet {
        int heightIndex;
        int heightMinusKIndex;
        int heightPlusKIndex;

        public SortedTriplet(int heightIndex, int heightMinusKIndex, int heightPlusKIndex) {
            this.heightIndex = heightIndex;
            this.heightMinusKIndex = heightMinusKIndex;
            this.heightPlusKIndex = heightPlusKIndex;
        }
    }

    private static class Wrestler {
        int height;
        int index;

        public Wrestler(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    private static class Query {
        int low;
        int high;
        int index;

        public Query(int low, int high, int index) {
            this.low = low;
            this.high = high;
            this.index = index;
        }
    }

    private static class FenwickTree {
        private int[] tree;

        public FenwickTree(int capacity) {
            this.tree = new int[capacity];
        }

        public void add(int index, int value) {
            for (; index < this.tree.length; index |= index + 1) {
                this.tree[index] += value;
            }
        }

        public int get(int index) {
            return this.sum(index, index);
        }

        public int sum(int startIndex, int endIndex) {
            return this.prefixSum(endIndex + 1) - this.prefixSum(startIndex);
        }

        private int prefixSum(int index) {
            int sum = 0;

            for (; index > 0; index &= index - 1) {
                sum += this.tree[index - 1];
            }

            return sum;
        }
    }
}