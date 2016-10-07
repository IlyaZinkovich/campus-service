package com.camp.campus.service;

import com.camp.campus.dto.SearchCriteria;
import com.camp.campus.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    Long createStudent(StudentDTO studentDTO);

    List<StudentDTO> getStudents(SearchCriteria searchCriteria, Integer page, Integer size);

    StudentDTO getStudentById(Long studentId);

    List<StudentDTO> getStudentsByIds(List<Long> studentIds);

    void updateStudentImage(Long studentId, String imagePath);
}
