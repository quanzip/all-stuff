package com.other_for_test.test_synchronize.sync_block;

public class Task extends Thread {
    String name;
    LockHolder lockHolder;

    public Task(String name, LockHolder lockHolder) {
        this.name = name;
        this.lockHolder = lockHolder;
        lockHolder.doThing(1);
        start();
    }

    @Override
    public void run() {
//        lockHolder.showSynchronized(this.name);
        lockHolder.printSynchronized(this.name);
    }
}
