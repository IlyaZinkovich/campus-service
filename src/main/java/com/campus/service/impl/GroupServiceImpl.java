package com.campus.service.impl;

import com.campus.dto.GroupDTO;
import com.campus.model.CampusGroup;
import com.campus.model.Student;
import com.campus.repository.GroupRepository;
import com.campus.repository.StudentRepository;
import com.campus.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Long> getStudentGroupIds(Long studentId) {
        return groupRepository.findGroupsByStudentId(studentId)
                .stream().map(CampusGroup::getId).collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> getGroups(Long studentId, Boolean joined) {
        List<CampusGroup> groups = Collections.EMPTY_LIST;
        if (studentId != null && joined != null) {
            groups = joined ? groupRepository.findGroupsByStudentId(studentId) :
                    groupRepository.findGroupsNotJoinedByStudentWithId(studentId);
        } else {
            groupRepository.findAll();
        }
        return groups.stream().map(this::groupToGroupDto).collect(Collectors.toList());
    }

    @Override
    public GroupDTO getGroup(Long groupId) {
        return groupToGroupDto(groupRepository.findOne(groupId));
    }

    @Override
    public void updateGroupImage(Long groupId, String imageUrl) {
        CampusGroup group = groupRepository.findOne(groupId);
        group.setImageUrl(imageUrl);
        groupRepository.save(group);
    }

    @Override
    public void deleteStudentFromGroup(Long groupId, Long studentId) {
        CampusGroup group = groupRepository.findOne(groupId);
        Student student = studentRepository.findOne(studentId);
        group.getStudents().remove(student);
        groupRepository.save(group);
    }

    @Override
    public void addStudentToGroup(Long groupId, Long studentId) {
        CampusGroup group = groupRepository.findOne(groupId);
        Student student = studentRepository.findOne(studentId);
        if (!group.getStudents().contains(student))
            group.getStudents().add(student);
        groupRepository.save(group);
    }

    private GroupDTO groupToGroupDto(CampusGroup group) {
        return new GroupDTO(group.getId(), group.getName(), group.getImageUrl());
    }
}
