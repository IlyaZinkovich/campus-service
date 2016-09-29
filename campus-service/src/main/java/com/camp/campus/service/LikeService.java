package com.camp.campus.service;

import com.camp.campus.model.Like;

import java.util.List;

public interface LikeService {

    List<Long> findStudentsWithMutualLike(Long studentId);

    boolean checkIfLikeExists(Like like);

    List<Long> findStudentIdsForMessageLikes(Long messageId);

    boolean saveLikeOrRemoveIfExists(Like like);
}
