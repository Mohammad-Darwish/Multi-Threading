package org.example.assignment;

import java.util.List;

class MultiExecutor{

    List<Runnable> runnableList;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        runnableList = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (int i = 0; i < runnableList.size(); i++) {
            new Thread(runnableList.get(i)).start();
        }
    }
}
