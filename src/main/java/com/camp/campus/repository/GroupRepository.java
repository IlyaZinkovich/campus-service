package com.camp.campus.repository;

import com.camp.campus.model.CampusGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<CampusGroup, Long> {

    @Query("select g from CampusGroup g inner join g.students s with s.id = :studentId")
    List<CampusGroup> findGroupsByStudentId(@Param("studentId") Long studentId);

    @Query("select distinct g from CampusGroup g where g.id not in " +
            "(select gi.id from CampusGroup gi inner join gi.students s with s.id = :studentId)")
    List<CampusGroup> findGroupsNotJoinedByStudentWithId(@Param("studentId") Long studentId);
}
