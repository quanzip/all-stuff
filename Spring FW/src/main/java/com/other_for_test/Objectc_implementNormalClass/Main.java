package com.other_for_test.Objectc_implementNormalClass;

public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass(){
            /**
             * implementing a NORMAL CLASS
             * we can re-define (overide) any methods we want.
             * */
            @Override
            public void showInfo() {
                System.out.println("showing info of: ");
                super.showInfo();
            }
        };

        myClass.showInfo();

        MyInterface myInterface = new MyInterface() {
            /**
             * Implementing an interface
             * we have to defind all ABSTRACT METHODS
             * */
            @Override
            public String getInfo() {
                return "hello Quanzip, you are implementing an interface";
            }
        };
    }


    public static class MyClass {
        public void showInfo() {
            System.out.println("Quanzip");
        }

    }

    static interface MyInterface{
        String getInfo();
    }
}
