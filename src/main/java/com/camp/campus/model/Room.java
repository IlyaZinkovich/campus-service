package com.camp.campus.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOMS")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_SEQ")
    @SequenceGenerator(name = "ROOM_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 10000)
    private Long id;

    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    private Integer capacity;

    private Integer floor;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE,
            CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private List<Student> students;

    public Room() {
    }

    public Room(Long id, String roomNumber, Integer capacity, Integer floor) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomNumber != null ? !roomNumber.equals(room.roomNumber) : room.roomNumber != null) return false;
        if (capacity != null ? !capacity.equals(room.capacity) : room.capacity != null) return false;
        return floor != null ? floor.equals(room.floor) : room.floor == null;

    }

    @Override
    public int hashCode() {
        int result = roomNumber != null ? roomNumber.hashCode() : 0;
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        return result;
    }
}
