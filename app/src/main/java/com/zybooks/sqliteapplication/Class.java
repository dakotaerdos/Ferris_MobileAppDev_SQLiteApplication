package com.zybooks.sqliteapplication;

public class Class {

    private Long id;
    private String name;
    private String description;
    private String location;
    private String instructor;
    private String date;
    private String time;


    public Class (Long id, String name, String description, String location, String instructor,
                  String date, String time) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.instructor = instructor;
        this.date = date;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
