package com.campus.service;

import com.campus.dto.StudentDTO;
import com.campus.dto.UserDTO;

public interface AuthenticationService {

    StudentDTO authenticate(UserDTO userDTO);
}
