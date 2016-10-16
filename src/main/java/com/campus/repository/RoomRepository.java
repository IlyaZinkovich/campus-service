package com.campus.repository;

import com.campus.model.Room;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long>, JpaSpecificationExecutor {

    Room findByRoomNumber(String roomNumber);
}
