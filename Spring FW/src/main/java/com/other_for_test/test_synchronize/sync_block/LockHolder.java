package com.other_for_test.test_synchronize.sync_block;

import java.util.Arrays;
import java.util.List;

public class LockHolder {
    final List<Integer> integers = Arrays.asList(1, 2, 3, 4);  // only 1 thread use this LIST at onece time

    public void showSynchronized(String name) {
        synchronized (this) {
            for (int i = 1; i < 6; i++) {
                System.out.println(name + " " + i);
            }
        }
    }

    public void printSynchronized(String name) {
//        final List<Integer> integers = Arrays.asList(1,2,3,4);  // local: mean nothing, it must be global variable then it takes effects

        synchronized (integers) {
            for (int i : integers) {
                System.out.println(name + " " + i);
            }
        }
    }

    public void doThing(int i) {
        System.out.println("dothing: " + (i * 100));
    }
}
