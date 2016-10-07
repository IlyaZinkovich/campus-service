package com.camp.campus.service.impl;

import com.camp.campus.dto.GroupDTO;
import com.camp.campus.dto.GroupMessageDTO;
import com.camp.campus.dto.GroupMessagePostDTO;
import com.camp.campus.model.CampusGroup;
import com.camp.campus.model.GroupMessage;
import com.camp.campus.repository.GroupMessageRepository;
import com.camp.campus.repository.GroupRepository;
import com.camp.campus.repository.LikeRepository;
import com.camp.campus.repository.StudentRepository;
import com.camp.campus.service.GroupMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupMessageServiceImpl implements GroupMessageService {

    @Autowired
    private GroupMessageRepository groupMessageRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<GroupMessageDTO> getGroupsMessages(List<Long> groupIds, Integer page, Integer size) {
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "postTime");
        List<GroupMessageDTO> groupMessages = groupMessageRepository.getGroupsMessages(groupIds, request)
                .stream().map(this::groupMessageToDto).collect(Collectors.toList());
        groupMessages.forEach(this::findMessageLikes);
        return groupMessages;
    }

    private void findMessageLikes(GroupMessageDTO message) {
        List<Long> studentIds = likeRepository.findStudentIdsForMessageLikes(message.getId());
        message.setLikesStudentIds(studentIds);
    }

    @Override
    public List<GroupMessageDTO> getGroupMessages(Long groupId, Integer page, Integer size) {
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "postTime");
        List<GroupMessageDTO> groupMessages = groupMessageRepository.getGroupMessages(groupId, request)
                .stream().map(this::groupMessageToDtoWithoutGroup).collect(Collectors.toList());
        groupMessages.forEach(this::findMessageLikes);
        return groupMessages;
    }

    @Override
    public void postGroupMessage(Long groupId, GroupMessagePostDTO dto) {
        CampusGroup group = groupRepository.findOne(groupId);
        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setMessage(dto.getMessage());
        groupMessage.setGroup(group);
        groupMessage.setFrom(studentRepository.findOne(dto.getStudentId()));
        groupMessage.setPostTime(LocalDateTime.now());
        group.getMessages().add(groupMessage);
        groupMessageRepository.save(groupMessage);
    }

    private GroupMessageDTO groupMessageToDtoWithoutGroup(GroupMessage groupMessage) {
        return new GroupMessageDTO(groupMessage.getId(), groupMessage.getMessage(), groupMessage.getPostTime(),
                groupMessage.getFrom().getFirstName() + " " + groupMessage.getFrom().getLastName());
    }

    private GroupMessageDTO groupMessageToDto(GroupMessage groupMessage) {
        return new GroupMessageDTO(groupMessage.getId(), groupMessage.getMessage(),
                groupToDto(groupMessage.getGroup()), groupMessage.getPostTime(),
                groupMessage.getFrom().getFirstName() + " " + groupMessage.getFrom().getLastName());
    }

    private GroupDTO groupToDto(CampusGroup group) {
        return new GroupDTO(group.getId(), group.getName(), group.getImageUrl());
    }
}
