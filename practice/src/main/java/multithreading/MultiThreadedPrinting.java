package multithreading;

public class MultiThreadedPrinting {

	private static final int NUM_THREADS = 10;

	private static final int MAX_VALUE = 100;

	private static volatile int current_val;

	private static final Object lock = new Object();

	static class PrintNum implements Runnable {

		private int thread_number;

		public PrintNum(int num) {
			this.thread_number = num;
		}

		@Override
		public void run() {
			while (true) {
				if (current_val > MAX_VALUE) {
					break;
				}
				if ((this.thread_number != NUM_THREADS && this.thread_number != (current_val % NUM_THREADS)) ||
						(this.thread_number == NUM_THREADS && (current_val % NUM_THREADS != 0))) {
					try {
						synchronized(lock) {
							lock.wait();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Thread-" + this.thread_number + " : "
							+ current_val);
					current_val += 1;
					synchronized (lock) {
						lock.notifyAll();
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		// print 1-100 using 10 threads
		// t1 prints 1,11,21,...,91
		// t2 prints 2,12,22,...,92
		// ..
		// t10 prints 10,20,30,...,100
		current_val = 1; // starting position
		for (int i = 1; i <= NUM_THREADS; i++) {
			new Thread(new PrintNum(i), "Thread-" + i).start();
		}
	}

}
