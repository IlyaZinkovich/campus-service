package com.camp.campus.repository.implementation;

import com.camp.campus.model.Room;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RoomSpecification implements Specification<Room> {

    private Integer floor;

    public RoomSpecification(Integer floor) {
        this.floor = floor;
    }

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.and();
        if (floor == null) return predicate;
        Predicate floor = criteriaBuilder.equal(root.get("floor"), this.floor);
        predicate = criteriaBuilder.and(predicate, floor);
        return predicate;
    }
}