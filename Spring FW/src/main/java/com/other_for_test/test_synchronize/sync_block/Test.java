package com.other_for_test.test_synchronize.sync_block;

public class Test {
    public static void main(String[] args) {
//        LockHolder lockHolder = new LockHolder();
//        new Task("Quan", lockHolder);
//        new Task("Thong", lockHolder);
//        new Task("Ngoan", lockHolder);
//        lockHolder.showSynchronized("Main");

        LockHolder lockHolder = new LockHolder();
        new Task("Quan", lockHolder);
        new Task("Thong", lockHolder);
        new Task("Ngoan", lockHolder);
        lockHolder.printSynchronized("Main");
    }
}
