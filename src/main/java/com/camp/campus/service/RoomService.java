package com.camp.campus.service;

import com.camp.campus.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> findAll(Integer floor, Integer page, Integer size);
}
