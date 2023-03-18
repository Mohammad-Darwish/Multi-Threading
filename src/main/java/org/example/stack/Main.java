package org.example.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Stack<Integer> stack = new LockFreeStack<>();
        Random random = new Random();
        List<Thread> threads = new ArrayList<>();
        int pushThreads = 2;
        int popThreads = 2;

        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }

        for (int i = 0; i < pushThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.push(random.nextInt());
                }
            });
            threads.add(thread);
        }

        for (int i = 0; i < popThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.pop();
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        Thread.sleep(100);
        System.out.println("The number of operation done is: " + stack.getCounter());
    }
}
