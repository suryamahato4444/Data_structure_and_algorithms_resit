//QUESTION-7A
package lang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final Lock[] CHOPSTICKS = new Lock[NUM_PHILOSOPHERS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            CHOPSTICKS[i] = new ReentrantLock();
        }
        Thread[] philosophers = new Thread[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            final int philosopherIndex = i;
            philosophers[i] = new Thread(() -> {
                while (true) {
                    try {
                        think(philosopherIndex);
                        eat(philosopherIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            philosophers[i].start();
        }
    }

    private static void think(int philosopherIndex) throws InterruptedException {
        System.out.println("Philosopher " + philosopherIndex + " is thinking");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private static void eat(int philosopherIndex) throws InterruptedException {
        int left = philosopherIndex;
        int right = (philosopherIndex + 1) % NUM_PHILOSOPHERS;
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }
        CHOPSTICKS[left].lock();
        System.out.println("Philosopher " + philosopherIndex + " picks up left chopstick");
        Thread.sleep((long) (Math.random() * 1000));
        CHOPSTICKS[right].lock();
        System.out.println("Philosopher " + philosopherIndex + " picks up right chopstick and eats");
        Thread.sleep((long) (Math.random() * 1000));
        CHOPSTICKS[right].unlock();
        System.out.println("Philosopher " + philosopherIndex + " puts down right chopstick");
        CHOPSTICKS[left].unlock();
        System.out.println("Philosopher " + philosopherIndex + " puts down left chopstick and finishes eating");
    }
}
