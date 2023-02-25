package com.other_for_test.test_synchronize.sync_method;

public class LockHolder {
    // all threads that see this method can access it at the same time
    public static void callBack(String name) {
        for (int i = 1; i < 10; i++) {
            System.out.println("thread: " + name + " - " + i);
        }
    }

    // SYNCHRONIZED: prevent multi thread access this method at the same time, when one thread is holding the lock(using method)
    // then NO others thread can access it. They have to wait until the lock get release and so on.
    public static synchronized void synchronizedCallBack(String name) {
        for (int i = 1; i < 10; i++) {
            System.out.println("thread: " + name + " - " + i);
        }
    }
}
