package com.campus.service;

import com.campus.dto.SearchCriteria;
import com.campus.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    Long createStudent(StudentDTO studentDTO);

    List<StudentDTO> getStudents(SearchCriteria searchCriteria, Integer page, Integer size);

    StudentDTO getStudentById(Long studentId);

    List<StudentDTO> getStudentsByIds(List<Long> studentIds);

    void updateStudentImage(Long studentId, String imagePath);
}
