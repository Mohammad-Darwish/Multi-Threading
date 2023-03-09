package org.example.hacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo {
    public static final int MAX_PASSWORD = 1_000_000;
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new DescendingHackerThread(vault));
        threadList.add(new AscendingHackerThread(vault));
        threadList.add(new PoliceThread());

        threadList.stream().forEach(thread -> thread.start());
    }

    protected static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrect(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e){
            }
            return password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            super.start();
            System.out.println("Starting thread with name:"+ this.getName());
        }
    }

    private static class AscendingHackerThread extends HackerThread{

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrect(guess)){
                    System.out.println(this.getName() + " guessed the password:" + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread{
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess > 0; guess--) {
                if (vault.isCorrect(guess)){
                    System.out.println(this.getName() + " guessed the password:" + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                }
                System.out.println(i);
            }
            System.out.println("Game over Hacker");
        }
    }
}
