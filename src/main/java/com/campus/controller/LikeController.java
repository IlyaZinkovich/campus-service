package com.campus.controller;

import com.campus.model.Like;
import com.campus.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "v1/likes", method = POST)
    public boolean like(@RequestBody Like like) {
        return likeService.saveLikeOrRemoveIfExists(like);
    }

    @RequestMapping(path = "v1/likes/students/{studentId}", method = GET)
    public List<Long> findStudentsIdsWithMutualLike(@PathVariable("studentId") Long studentId) {
        return likeService.findStudentsWithMutualLike(studentId);
    }

    @RequestMapping(path = "v1/likes", method = GET)
    public boolean checkIfLikeExists(@RequestParam("from") Long from, @RequestParam("to") Long to,
                           @RequestParam("type") String type) {
        return likeService.checkIfLikeExists(new Like(from, to, Like.LikeType.valueOf(type)));
    }

    @RequestMapping(path = "v1/likes/students", method = GET)
    public List<Long> findStudentIdsForMessageLikes(@RequestParam("messageId") Long messageId) {
        return likeService.findStudentIdsForMessageLikes(messageId);
    }
}
