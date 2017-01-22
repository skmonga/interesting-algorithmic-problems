package multithreading;

public class ThreadExample implements Runnable {
public void run() {
for (int i = 0; i < 3; i++)
System.out.println(i);
}
public static void main(String[] args) {
new Thread(new ThreadExample()).start();
new Thread( new ThreadExample()).start();
System.out.println("Done");
}
}