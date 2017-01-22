package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerrank.com/challenges/fraudulent-activity-notifications
 * This problem uses counting sort as the amount spent is max 200 so btw 0-200.
 */
public class FraudulentActivityNotifications {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int n = Integer.parseInt(arr[0]);
		int d = Integer.parseInt(arr[1]);
		int[] num = new int[n];
		arr = br.readLine().split(" ");
		for(int i = 0; i < n; i++)
			num[i] = Integer.parseInt(arr[i]);
		
		int num_notifications = 0;
		int[] frequency = new int[201];  //all frequency 0 initially
		int i = 0;
		while(i < d) {
			frequency[num[i]] += 1;
			i++;
		}
		
		//now we check for notification
		while(i < n) {
			float median = findMedian(frequency,d);
			if(num[i] >= median * 2)
				num_notifications += 1;
			
			frequency[num[i]] += 1;
			frequency[num[i-d]] -= 1;
			i++;
		}
		System.out.println(num_notifications);
		
	}

	private static float findMedian(int[] frequency, int d) {
		boolean checkEven = (d%2 == 0);
		int index = d/2 + (checkEven?0:1);
		int freq_count = 0;
		for(int i = 0; i < frequency.length; i++) {
			if(freq_count + frequency[i] >= index) {
				if(!checkEven || freq_count + frequency[i] > index)
					return i;
				else {
					//freq_count + frequency[i] == index
					//find next element whose frequency is not 0
					int j = i+1;
					while(frequency[j] == 0)
						j++;
					return (((float)i+ (float)j)/2);
				}
			} else {
				freq_count += frequency[i];
			}
		}
		return 0;
	}
}
