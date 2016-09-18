package com.camp.campus.controller;

import com.camp.campus.dto.GroupDTO;
import com.camp.campus.dto.GroupMessageDTO;
import com.camp.campus.dto.GroupMessagePostDTO;
import com.camp.campus.dto.StudentDTO;
import com.camp.campus.service.GroupMessageService;
import com.camp.campus.service.GroupService;
import com.camp.campus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class GroupMessageController {

    @Autowired
    private GroupMessageService groupMessageService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "v1/groups/messages", method = GET)
    public List<GroupMessageDTO> getGroupsMessages(@RequestParam(required = false) Long studentId) {
        List<Long> studentGroupIds = groupService.getStudentGroupIds(studentId);
        return groupMessageService.getGroupsMessages(studentGroupIds);
    }

    @RequestMapping(path = "v1/groups/{groupId}/messages", method = GET)
    public List<GroupMessageDTO> getGroupMessages(@PathVariable("groupId") Long groupId) {
        return groupMessageService.getGroupMessages(groupId);
    }

    @RequestMapping(path = "v1/groups/{groupId}/messages", method = POST)
    public void postGroupMessage(@PathVariable("groupId") Long groupId, @RequestBody GroupMessagePostDTO dto) {
        groupMessageService.postGroupMessage(groupId, dto);
    }
}
