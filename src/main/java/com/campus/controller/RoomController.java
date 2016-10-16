package com.campus.controller;

import com.campus.dto.RoomDTO;
import com.campus.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(path = "v1/rooms", method = GET)
    public List<RoomDTO> getRooms(@RequestParam(required = false) Integer floor, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return roomService.findAll(floor, page, size);
    }
}
