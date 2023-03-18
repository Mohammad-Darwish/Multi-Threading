package org.example.stack;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> implements Stack<T> {

    private AtomicInteger counter = new AtomicInteger();
    private AtomicReference<StackNode<T>> head = new AtomicReference<>();

    @Override
    public void push(T newValue) {
        StackNode<T> newStackNode = new StackNode<>(newValue);

        while (true) {
            StackNode<T> currentHead = head.get();
            newStackNode.next = currentHead;
            if (head.compareAndSet(currentHead, newStackNode)) {
                break;
            } else {
                LockSupport.parkNanos(2);
            }
        }
        counter.addAndGet(1);
    }

    @Override
    public T pop() {
        StackNode<T> currentHead = head.get();
        StackNode<T> newHead;
        while (currentHead != null) {
            newHead = currentHead.next;
            if (head.compareAndSet(currentHead, newHead)) {
                break;
            } else {
                currentHead = head.get();
            }
        }
        counter.addAndGet(1);
        return currentHead != null ? currentHead.value : null;
    }

    @Override
    public int getCounter() {
        return counter.get();
    }
}
