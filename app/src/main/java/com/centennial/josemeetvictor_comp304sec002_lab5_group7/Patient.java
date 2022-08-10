package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;
    private int bill;

    public Patient ()
    {

    }
    public Patient(int id, String name, int age, String disease, int bill)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }
}
