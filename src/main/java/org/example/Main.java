package org.example;

public class Main {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Thread name:" + Thread.currentThread().getName());
        thread.start();
        System.out.println("Thread name:" + Thread.currentThread().getName());
    }

    private static class NewThread extends Thread{
        public void run() {
            System.out.println("A new thread name:" + Thread.currentThread().getName());
        }
    }
}