package com.other_for_test.test_static;

public class ClassTest1 {
    static private int id;

    static {
        id = 6;
        System.out.println(id);
    }

    public ClassTest1() {
        id = 10;
        System.out.println("Initializing construction>>>");
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ClassTest1.id = id;
    }

    public void showInfo() {
        System.out.println("ID is: " + id);
    }
}
