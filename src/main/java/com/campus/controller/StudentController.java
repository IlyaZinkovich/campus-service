package com.campus.controller;

import com.campus.dto.SearchCriteria;
import com.campus.dto.StudentDTO;
import com.campus.service.LikeService;
import com.campus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "v1/students", method = POST)
    public Long createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @RequestMapping(path = "v1/students", method = GET)
    public List<StudentDTO> getStudents(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String gender,
                                        @RequestParam(required = false) Integer ageLow,
                                        @RequestParam(required = false) Integer ageHigh,
                                        @RequestParam(required = false) String faculty,
                                        @RequestParam(required = false) String speciality,
                                        @RequestParam(required = false) Integer course,
                                        @RequestParam(required = false) Integer group,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer size,
                                        @RequestParam(required = false) List<Long> studentIds) {
        if (studentIds != null && !studentIds.isEmpty()) {
            return studentService.getStudentsByIds(studentIds);
        }
        return studentService.getStudents(new SearchCriteria(name, gender, ageLow, ageHigh, faculty, speciality, course, group), page, size);
    }

    @RequestMapping(path = "v1/students/{studentId}", method = GET)
    public StudentDTO getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(path = "v1/students/{studentId}/image", method = PUT)
    public void updateStudentImage(@PathVariable Long studentId, @RequestBody String imagePath) {
        studentService.updateStudentImage(studentId, imagePath);
    }

    @RequestMapping(path = "v1/students/likes/{studentId}", method = GET)
    public List<StudentDTO> findStudentsWithMutualLike(@PathVariable("studentId") Long studentId) {
        List<Long> studentIds = likeService.findStudentsWithMutualLike(studentId);
        return studentService.getStudentsByIds(studentIds);
    }
}
