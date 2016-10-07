package com.camp.campus.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
public class Student {

    public enum Gender {
        MALE, FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ")
    @SequenceGenerator(name = "STUDENT_SEQ", sequenceName = "STUDENT_SEQ", allocationSize = 10000)
    private Long id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate birthDate;

    private String faculty;

    private String speciality;

    private Integer course;

    @Column(name = "GROUP_NUMBER")
    private Integer group;

    private String imagePath;

    @ManyToOne(optional = false)
    private Room room;

    @ManyToMany(mappedBy = "students")
    private List<CampusGroup> groups;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, Gender gender, LocalDate birthDate, String faculty, String speciality, Integer course, Integer group, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.faculty = faculty;
        this.speciality = speciality;
        this.course = course;
        this.group = group;
        this.imagePath = imagePath;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<CampusGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<CampusGroup> groups) {
        this.groups = groups;
    }

    public static class StudentBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private Gender gender;
        private LocalDate birthDate;
        private String faculty;
        private String speciality;
        private Integer course;
        private Integer group;
        private String imagePath;

        public StudentBuilder() {

        }

        public StudentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StudentBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public StudentBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public StudentBuilder faculty(String faculty) {
            this.faculty = faculty;
            return this;
        }

        public StudentBuilder speciality(String speciality) {
            this.speciality = speciality;
            return this;
        }

        public StudentBuilder course(Integer course) {
            this.course = course;
            return this;
        }

        public StudentBuilder group(Integer group) {
            this.group = group;
            return this;
        }

        public StudentBuilder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Student build() {
            return new Student(id, firstName, lastName, gender, birthDate,
                    faculty, speciality, course, group, imagePath);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        if (gender != student.gender) return false;
        if (birthDate != null ? !birthDate.equals(student.birthDate) : student.birthDate != null) return false;
        if (faculty != null ? !faculty.equals(student.faculty) : student.faculty != null) return false;
        if (speciality != null ? !speciality.equals(student.speciality) : student.speciality != null) return false;
        if (course != null ? !course.equals(student.course) : student.course != null) return false;
        return group != null ? group.equals(student.group) : student.group == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
