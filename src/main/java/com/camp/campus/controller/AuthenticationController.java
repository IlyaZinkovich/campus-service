package com.camp.campus.controller;

import com.camp.campus.dto.StudentDTO;
import com.camp.campus.dto.UserDTO;
import com.camp.campus.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(path = "v1/auth", method = POST)
    public StudentDTO authenticate(@RequestBody UserDTO user) {
        return authenticationService.authenticate(user);
    }
}
