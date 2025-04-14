package threading;

public class SingleThread_Runneble implements Runnable {
	public void run() {
		System.out.println("Thread using Runnable is running...........");
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new SingleThread_Runneble());
		t1.start();
	}
}



