package com.camp.campus.repository;

import com.camp.campus.model.Like;

import java.util.List;

public interface LikeRepository {

    List<Long> findStudentIdsWithMutualLike(Long studentId);

    List<Long> findStudentIdsForMessageLikes(Long messageId);

    boolean saveLikeOrRemoveIfExists(Like like);

    boolean checkIfLikeExists(Like like);
}
