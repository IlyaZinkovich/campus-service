package com.camp.campus.controller;

import com.camp.campus.dto.GroupDTO;
import com.camp.campus.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(path = "v1/groups", method = GET)
    public List<GroupDTO> getGroups(@RequestParam(required = false) Long studentId,
                                    @RequestParam(required = false) Boolean joined) {
        return groupService.getGroups(studentId, joined);
    }

    @RequestMapping(path = "v1/groups/{id}", method = GET)
    public GroupDTO getGroup(@PathVariable("id") Long id) {
        return groupService.getGroup(id);
    }

    @RequestMapping(path = "v1/groups/{groupId}/image", method = PUT)
    public void updateGroupImage(@PathVariable("groupId") Long groupId, @RequestBody String imageUrl) {
        groupService.updateGroupImage(groupId, imageUrl);
    }

    @RequestMapping(path = "v1/groups/{groupId}/students", method = PUT)
    public void deleteStudentFromGroup(@PathVariable("groupId") Long groupId, @RequestParam("studentId") Long studentId,
                                       @RequestParam("action") String action) {
        if ("DELETE".equals(action)) {
            groupService.deleteStudentFromGroup(groupId, studentId);
        } else if ("ADD".equals(action)) {
            groupService.addStudentToGroup(groupId, studentId);
        }
    }
}
