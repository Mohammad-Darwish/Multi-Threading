package org.example.stack;

public class StandardStack<T> implements Stack<T> {

    private int counter;
    private StackNode<T> head;

    @Override
    public synchronized void push(T newValue) {
        StackNode<T> newNode = new StackNode<>(newValue);
        newNode.next = head;
        head = newNode;
        counter++;
    }

    @Override
    public synchronized T pop() {
        if (head == null) {
            counter++;
            return null;
        }
        T currentValue = head.value;
        head = head.next;
        counter++;
        return currentValue;
    }

    @Override
    public int getCounter() {
        return counter;
    }
}
