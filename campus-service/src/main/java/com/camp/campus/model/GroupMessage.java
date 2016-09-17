package com.camp.campus.model;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_MESSAGE")
public class GroupMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUP_MESSAGE_SEQ")
    @SequenceGenerator(name = "GROUP_MESSAGE_SEQ", sequenceName = "GROUP_MESSAGE_SEQ", allocationSize = 1)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private CampusGroup group;

    @OneToOne(optional = false)
    @JoinColumn(name = "STUDENT_ID")
    private Student from;

    public GroupMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CampusGroup getGroup() {
        return group;
    }

    public void setGroup(CampusGroup group) {
        this.group = group;
    }

    public Student getFrom() {
        return from;
    }

    public void setFrom(Student from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMessage that = (GroupMessage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
