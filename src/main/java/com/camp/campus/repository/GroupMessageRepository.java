package com.camp.campus.repository;

import com.camp.campus.model.GroupMessage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupMessageRepository extends PagingAndSortingRepository<GroupMessage, Long> {

    @Query("select gm from GroupMessage gm where gm.group.id in :groupIds")
    List<GroupMessage> getGroupsMessages(@Param("groupIds") List<Long> groupIds, Pageable pageable);

    @Query("select gm from GroupMessage gm where gm.group.id = :groupId")
    List<GroupMessage> getGroupMessages(@Param("groupId") Long groupId, Pageable pageable);
}
