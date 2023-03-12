package org.example.power;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200"), new BigInteger("2000000")));
        thread.start();
        thread.interrupt(); // if the thread is not throwing interrupted exception, it will not do anything. So needs to do it explicitly in the thread.
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger pow) {
            this.base = base;
            this.power = pow;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + getPow(base, power));
        }

        private BigInteger getPow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) { // if the code does not throw interrupted exception.
                    System.out.println("Permanently interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
