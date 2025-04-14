/**
 * 
 */
package threading;


class TaskOne extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("TaskOne - Count: " + i);
            try {
                Thread.sleep(500); // sleep for half a second
            } catch (InterruptedException e) {
                System.out.println("TaskOne interrupted");
            }
        }
    }
}

class TaskTwo implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("TaskTwo - Count: " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("TaskTwo interrupted");
            }
        }
    }
}

public class MultiThreadingExample {

    public static void main(String[] args) {
        // Thread 1 - by extending Thread
        TaskOne t1 = new TaskOne();

        // Thread 2 - by implementing Runnable
        Thread t2 = new Thread(new TaskTwo());

        // Thread 3 - using lambda
        Thread t3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Task - Count: " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("Lambda Task interrupted");
                }
            }
        });

        // Start all threads
        t1.start();
        t2.start();
        t3.start();

        System.out.println("Main thread finished starting all tasks.");
    }
}
