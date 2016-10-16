package com.campus.service.impl;

import com.campus.dto.StudentDTO;
import com.campus.dto.UserDTO;
import com.campus.exceptions.AuthenticationException;
import com.campus.model.Student;
import com.campus.model.User;
import com.campus.repository.UserRepository;
import com.campus.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentDTO authenticate(UserDTO userDTO) {
        User user = userRepository.findByLogin(userDTO.getLogin());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return profileToDto(user.getProfile());
        }
        throw new AuthenticationException();
    }

    private StudentDTO profileToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGender(student.getGender().name());
        studentDTO.setBirthDate(student.getBirthDate());
        studentDTO.setFaculty(student.getFaculty());
        studentDTO.setSpeciality(student.getSpeciality());
        studentDTO.setCourse(student.getCourse());
        studentDTO.setGroup(student.getGroup());
        studentDTO.setRoomNumber(student.getRoom().getRoomNumber());
        studentDTO.setImagePath(student.getImagePath());
        return studentDTO;
    }
}
