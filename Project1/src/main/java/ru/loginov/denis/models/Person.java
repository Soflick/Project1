package ru.loginov.denis.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, message = "Имя должно быть не короче 2 символов")
    private String name;

    @Min(value = 1, message = "Возраст должен быть больше 0")
    private int year;

    public Person (){

    }

    public Person(int id, String name, int year) {
        this.id=id;
        this.name = name;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
