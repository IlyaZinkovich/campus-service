package com.camp.campus.repository;

import com.camp.campus.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByProfileId(Long profileId);
}
