package com.campus.dto;

import java.util.List;

public class SearchCriteria {

    private String name;
    private String gender;
    private Integer ageLow;
    private Integer ageHigh;
    private String faculty;
    private String speciality;
    private Integer course;
    private Integer group;

    public SearchCriteria(String name, String gender, Integer ageLow, Integer ageHigh, String faculty, String speciality, Integer course, Integer group) {
        this.name = name;
        this.gender = gender;
        this.ageLow = ageLow;
        this.ageHigh = ageHigh;
        this.faculty = faculty;
        this.speciality = speciality;
        this.course = course;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAgeLow() {
        return ageLow;
    }

    public void setAgeLow(Integer ageLow) {
        this.ageLow = ageLow;
    }

    public Integer getAgeHigh() {
        return ageHigh;
    }

    public void setAgeHigh(Integer ageHigh) {
        this.ageHigh = ageHigh;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
}
