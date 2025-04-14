/**
 * 
 */
package threading;

/**
 * 
 */
public class SingleThread_Thread extends Thread {

	public void run() {

		System.out.println(" Thread is running...");

	}

	public static void main(String[] args) {
		SingleThread_Thread t1 = new SingleThread_Thread();
		t1.start(); // starts a new thread
	}
}
