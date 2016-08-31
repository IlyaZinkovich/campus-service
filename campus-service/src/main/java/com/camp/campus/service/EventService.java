package com.camp.campus.service;

import com.camp.campus.dto.EventDTO;
import com.camp.campus.dto.LikeEventDTO;

import java.util.List;

public interface EventService {

    List<EventDTO> findByProfileId(Long profileId);
    void saveLikeEvent(LikeEventDTO event);
}
