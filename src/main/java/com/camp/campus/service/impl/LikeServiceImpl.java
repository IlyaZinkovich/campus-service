package com.camp.campus.service.impl;

import com.camp.campus.model.Like;
import com.camp.campus.repository.LikeRepository;
import com.camp.campus.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Long> findStudentsWithMutualLike(Long studentId) {
        return likeRepository.findStudentIdsWithMutualLike(studentId);
    }

    @Override
    public boolean checkIfLikeExists(Like like) {
        return likeRepository.checkIfLikeExists(like);
    }

    @Override
    public List<Long> findStudentIdsForMessageLikes(Long messageId) {
        return likeRepository.findStudentIdsForMessageLikes(messageId);
    }

    @Override
    public boolean saveLikeOrRemoveIfExists(Like like) {
        return likeRepository.saveLikeOrRemoveIfExists(like);
    }
}
