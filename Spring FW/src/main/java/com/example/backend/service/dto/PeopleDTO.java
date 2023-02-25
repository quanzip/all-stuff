package com.example.backend.service.dto;

public class PeopleDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private int pNumber;

    public PeopleDTO() {
    }

    public PeopleDTO(Long id, String firstName, String lastName, int pNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNumber = pNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pNumber=" + pNumber +
                '}';
    }
}

