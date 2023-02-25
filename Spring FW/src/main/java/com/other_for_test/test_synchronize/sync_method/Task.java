package com.other_for_test.test_synchronize.sync_method;

public class Task extends Thread {

    String name;

    public Task(String name) {
        this.name = name;
        start();
    }

    @Override
    public void run() {
        LockHolder.callBack(this.name);
    }
}
