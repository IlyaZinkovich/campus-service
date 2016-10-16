package com.campus.controller;

import com.campus.dto.GroupMessageDTO;
import com.campus.dto.GroupMessagePostDTO;
import com.campus.service.GroupMessageService;
import com.campus.service.GroupService;
import com.campus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<GroupMessageDTO> getGroupsMessages(@RequestParam(required = false) Long studentId,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false) Integer size) {
        List<Long> studentGroupIds = groupService.getStudentGroupIds(studentId);
        return groupMessageService.getGroupsMessages(studentGroupIds, page, size);
    }

    @RequestMapping(path = "v1/groups/{groupId}/messages", method = GET)
    public List<GroupMessageDTO> getGroupMessages(@PathVariable("groupId") Long groupId,
                                                  @RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size) {
        return groupMessageService.getGroupMessages(groupId, page, size);
    }

    @RequestMapping(path = "v1/groups/{groupId}/messages", method = POST)
    public void postGroupMessage(@PathVariable("groupId") Long groupId, @RequestBody GroupMessagePostDTO dto) {
        groupMessageService.postGroupMessage(groupId, dto);
    }
}
