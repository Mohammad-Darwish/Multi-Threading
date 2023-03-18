package org.example.stack;

public interface Stack<T> {
    void push(T newValue);

    T pop();

    int getCounter();
}