package com.camp.campus.model;

public class Like {

    private Long from;
    private Long to;
    private LikeType type;

    public enum LikeType {
        STUDENT_TO_STUDENT, STUDENT_TO_MESSAGE
    }

    public Like() {
    }

    public Like(Long from, Long to, LikeType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public LikeType getType() {
        return type;
    }

    public void setType(LikeType type) {
        this.type = type;
    }
}
