package com.example.backend.config;

public class TestCOnfig {
    private String name;
    private String add;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "TestCOnfig{" +
                "name='" + name + '\'' +
                ", add='" + add + '\'' +
                '}';
    }
}
