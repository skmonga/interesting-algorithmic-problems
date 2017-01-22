package code.practice;

/**
 * Refer to:
 * http://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
 * Problem: Find all the subsets of a given set.
 * 
 * Input: S = {a, b, c, d} Output: {}, {a} , {b}, {c}, {d}, {a,b}, {a,c}, {a,d},
 * {b,c}, {b,d}, {c,d}, {a,b,c}, {a,b,d}, {a,c,d}, {b,c,d}, {a,b,c,d}
 * So {} can be seen as 0000,{a} as 0001 ,{a,b,c,d} as 1111
 * 
 * 
 */
public class PrintSubsets {

	static void printSubsets(char set[])
    {
        int n = set.length;
 
        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1<<n); i++)
        {
            System.out.print("{ ");
 
            // Print current subset
            for (int j = 0; j < n; j++)
 
                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0)
                    System.out.print(set[j] + " ");
 
            System.out.println("}");
        }
    }
 
    // Driver code
    public static void main(String[] args)
    {
        char set[] = {'a', 'b', 'c','d'};
        printSubsets(set);
    }
}
