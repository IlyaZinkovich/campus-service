package com.camp.campus.service;

import com.camp.campus.dto.StudentDTO;
import com.camp.campus.dto.UserDTO;

public interface AuthenticationService {

    StudentDTO authenticate(UserDTO userDTO);
}
