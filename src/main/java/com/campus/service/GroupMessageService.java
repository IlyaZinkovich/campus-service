package com.campus.service;

import com.campus.dto.GroupMessageDTO;
import com.campus.dto.GroupMessagePostDTO;

import java.util.List;

public interface GroupMessageService {

    List<GroupMessageDTO> getGroupsMessages(List<Long> studentGroupIds, Integer page, Integer size);

    List<GroupMessageDTO> getGroupMessages(Long groupId, Integer page, Integer size);

    void postGroupMessage(Long groupId, GroupMessagePostDTO dto);
}
