/**
 * 
 */
package threading;

/**
 * 
 */
public class SingleThread extends Thread {

	public void run() {
		System.out.println("Thread is running...");
	}

	public static void main(String[] args) {
		SingleThread t1 = new SingleThread();
		t1.start(); // starts a new thread
		
		try {
			t1.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SingleThread s2=new SingleThread();
		
		s2.start();
	}
}
