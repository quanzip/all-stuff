package com.other_for_test.TestPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> p = (x) -> x != 0;
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 0, 4, 6, 0));
        list.removeIf(p);
        System.out.println(list);
    }
}
