package code.practice;

/**
 * Refer to :
 * http://www.geeksforgeeks.org/find-minimum-time-to-finish-all-jobs-with-given-
 * constraints/
 * 
 * Given an array of jobs with different time requirements. There are K
 * identical assignees available and we are also given how much time an assignee
 * takes to do one unit of job. Find the minimum time to finish all jobs with
 * following constraints.
 * 
 * An assignee can be assigned only contiguous jobs. For example, an assignee
 * cannot be assigned jobs 1 and 3, but not 2. Two assignees cannot share (or
 * co-assigned) a job, i.e., a job cannot be partially assigned to one assignee
 * and partially to other.
 *
 * The problem can be seen as the breaking job array into k subarrays such that
 * maximum sum of subarray is minimized.
 */
public class MinTimeToFinishJobs {

	private static int getSubarraySum(int[] jobs,int cur_index, int end_index) {
		int sum = 0;
		for(int i = cur_index; i <= end_index; i++)
			sum += jobs[i];
		return sum;
	}
	
	private static int getOptimalSum(int[] jobs,int n, int part_num, int cur_index, int num_parts) {
		if(cur_index == n || part_num == num_parts) {
			return 0;
		}
		int min_value = Integer.MAX_VALUE,end_index;
		for(int i = cur_index;i <= n-num_parts+part_num; i++) {
			//last partition
			if(part_num == num_parts-1) {
				end_index = n-1;
			} else {
				end_index = i;
			}
			int subarray_sum = getSubarraySum(jobs,cur_index,end_index);
			int optimal_rest = getOptimalSum(jobs, n, part_num+1, i+1, num_parts);
			optimal_rest = (optimal_rest > subarray_sum)?optimal_rest:subarray_sum;
			if(optimal_rest < min_value)
				min_value = optimal_rest;
		}
		return min_value;
	}
	

	private static int minTimeToComplete(int k, int timeunit, int[] jobs) {
		int minSumPartition = getOptimalSum(jobs,jobs.length,0,0,k);
		return minSumPartition*timeunit;
	}

	public static void main(String[] args) {
		int k = 4, T = 5;
		int[] job = {10, 7, 8, 12, 6, 8};
		System.out.println(minTimeToComplete(k, T, job));
	}

}
