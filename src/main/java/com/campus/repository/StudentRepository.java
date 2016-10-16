package com.campus.repository;

import com.campus.model.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>, JpaSpecificationExecutor {

    @Query("select s from Student s where s.id in :ids")
    List<Student> findByIds(@Param("ids") List<Long> ids);
}
