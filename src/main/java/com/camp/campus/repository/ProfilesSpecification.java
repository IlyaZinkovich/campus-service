package com.camp.campus.repository;


import com.camp.campus.dto.SearchCriteria;
import com.camp.campus.model.Profile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class ProfilesSpecification implements Specification<Profile> {

    private SearchCriteria criteria;

    public ProfilesSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.and();
        if (criteria == null) return predicate;
        if (criteria.getName() != null) {
            Predicate firstName = criteriaBuilder.like(root.get("firstName"), criteria.getName());
            Predicate lastName = criteriaBuilder.like(root.get("lastName"), criteria.getName());
            Predicate name = criteriaBuilder.or(firstName, lastName);
            predicate = criteriaBuilder.and(predicate, name);
        }
        if (criteria.getGender() != null) {
            Predicate gender = criteriaBuilder.equal(root.get("gender"), Profile.Gender.valueOf(criteria.getGender()).ordinal());
            predicate = criteriaBuilder.and(predicate, gender);
        }
        if (criteria.getAgeLow() != null) {
            Predicate ageLow = criteriaBuilder.lessThanOrEqualTo(root.get("birthDate"), LocalDate.now().minusYears(criteria.getAgeLow()));
            predicate = criteriaBuilder.and(predicate, ageLow);
        }
        if (criteria.getAgeHigh() != null) {
            Predicate ageLow = criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), LocalDate.now().minusYears(criteria.getAgeHigh() + 1));
            predicate = criteriaBuilder.and(predicate, ageLow);
        }
        if (criteria.getFaculty() != null) {
            Predicate faculty = criteriaBuilder.equal(root.get("faculty"), criteria.getFaculty());
            predicate = criteriaBuilder.and(predicate, faculty);
        }
        if (criteria.getSpeciality() != null) {
            Predicate speciality = criteriaBuilder.equal(root.get("speciality"), criteria.getSpeciality());
            predicate = criteriaBuilder.and(predicate, speciality);
        }
        if (criteria.getCourse() != null) {
            Predicate course = criteriaBuilder.equal(root.get("course"), criteria.getCourse());
            predicate = criteriaBuilder.and(predicate, course);
        }
        if (criteria.getGroup() != null) {
            Predicate group = criteriaBuilder.equal(root.get("group"), criteria.getGroup());
            predicate = criteriaBuilder.and(predicate, group);
        }
        return predicate;
    }
}
