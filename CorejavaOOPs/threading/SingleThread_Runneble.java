package threading;

public class SingleThread_Runneble implements Runnable {

	public void run() {
		System.out.println("Thread using Runnable is running..........1");
	}

	public static void main(String[] args) {

		// 1. Thread with Runnable (current class)
		Thread t1 = new Thread(new SingleThread_Runneble());
		t1.start();

		// 2. Thread with anonymous Runnable class
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Thread using anonymous Runnable is running  ..........2");
			}
		});
		t2.start();

		// 3. Thread with Lambda
		Thread t3 = new Thread(() -> System.out.println("Thread using Lambda Runnable is running...........3"));
		t3.start();

		
		Thread t4 = new Thread(() -> count());
		t4.start();
	}

	public static void count() {
		int countt = 0;
		for (int i = 0; countt < 20; i++) {
			countt++;
			System.out.println(countt);
		}
	}

}
