package code.practice;


/**
 * @author skm
 *
 *This doesn't handle the case where consecutive equal values appear.
 *When consecutive equal values appear,it may be that there is a non dec or non inc sequence
 *going on or the neither is occuring.
 *For an example of such failure ,check with {1,1,2}
 */
public class QuoraUpvotes {

	private int N;
	
	private int k;
	
	private int[] num;
	
	public QuoraUpvotes(int size,int window,int[] arr) {
		this.N = size;
		this.k = window;
		this.num = arr;
	}
	
	class Pair {
		int nonDec;
		int nonInc;
		
		public Pair() {
			
		}
	}
	
	private Pair[] initialNonDecMinusNonInc(Boolean isNonDec,
			Boolean isNonInc,Integer seqLength) {
		//do for first window
		int i = 1;
		int[] arr = this.num;
		Pair[] pairs = new Pair[this.N];
		for(int j = 0; j < N; j++) {
			pairs[j] = new Pair();
			pairs[j].nonDec = 0;
			pairs[j].nonInc = 0;
		}
		/*boolean isNonDec = false,isNonInc = false;
		int seqLength = 1;*/
		
		while(i < this.k) {
			//non decreasing
			if(arr[i] > arr[i-1]) {
				if(isNonDec) {
					seqLength += 1;
					for(int j = i-1; j >= i-seqLength; j--) {
						pairs[j].nonDec += 1;
					}
				} else {
					isNonDec = true;
					pairs[i-1].nonDec += 1;
					seqLength = 1;
				}
			} else if(arr[i] < arr[i-1]) {
				//non increasing
				if(isNonInc) {
					seqLength += 1;
					for(int j = i-1; j >= i-seqLength; j--) {
						pairs[j].nonInc += 1;
					}
				} else {
					isNonInc = true;
					pairs[i-1].nonInc += 1;
					seqLength = 1;
				}
			} else {
				//equal numbers
				/*int elem = arr[i];
				if(isNonDec) {
					//coming from nondecreasing sequence
					seqLength += 1;
					for(int j = i-1; j >= i-seqLength; j--) {
						pairs[j].nonDec += 1;
						if(arr[j] == elem)
							pairs[j].nonInc += 1;
							
					}
				}
				if(isNonInc) {
					//coming from nonincreasing sequence
					seqLength += 1;
					for(int j = i-1; j >= i-seqLength; j--) {
						pairs[j].nonInc += 1;
						if(arr[j] == elem)
							pairs[j].nonDec += 1;
							
					}
				} */
			}
			i += 1;
		}
		return pairs;
	}
	
	public void NonDecMinusNonInc() {
		Boolean isNonDec = false,isNonInc = false;
		Integer seqLength = 0;
		Pair[] pairs = initialNonDecMinusNonInc(isNonDec, isNonInc, seqLength);
		int[] arr = this.num;
		int nonDecRanges = 0,nonIncRanges = 0;
		for(int i = 0; i < this.k ; i++) {
			nonDecRanges += pairs[i].nonDec;
			nonIncRanges += pairs[i].nonInc;
		}
		System.out.println("The output is as follows :");
		System.out.println(nonDecRanges - nonIncRanges);
		for(int i = this.k ; i < this.N ; i++) {
			if(arr[i] > arr[i-1]) {
				if(isNonDec) {
					seqLength += 1;
					for(int j = i-1; j >= i-seqLength; j--) {
						pairs[j].nonDec += 1;
					}
				} else {
					isNonDec = true;
					pairs[i-1].nonDec += 1;
					seqLength = 1;
				}
			} else if(arr[i] < arr[i-1]) {
				if(isNonInc) {
					seqLength += 1;
					for(int j = i-1; j >= i-seqLength; j--) {
						pairs[j].nonInc += 1;
					}
				} else {
					isNonInc = true;
					pairs[i-1].nonInc += 1;
					seqLength = 1;
				}
			} else {
				
			}
			nonDecRanges = nonDecRanges - pairs[i-this.k].nonDec + pairs[i-1].nonDec;
			nonIncRanges = nonIncRanges - pairs[i-this.k].nonInc + pairs[i-1].nonInc;
			System.out.println(nonDecRanges - nonIncRanges);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,1,1};
//		int[] arr = {1,1,2};
		QuoraUpvotes upvotes = new QuoraUpvotes(5,3,arr);
//		QuoraUpvotes upvotes = new QuoraUpvotes(3,3,arr);
		upvotes.NonDecMinusNonInc();
		
	}
}