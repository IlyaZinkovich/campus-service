package com.camp.campus.repository.implementation;

import com.camp.campus.model.Like;
import com.camp.campus.model.Like.LikeType;
import com.camp.campus.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Collections.singletonMap;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Long> findStudentIdsWithMutualLike(Long studentId) {
        return jdbcTemplate.queryForList("SELECT SL.TO_ID FROM STUDENT_LIKES SL WHERE SL.FROM_ID=:studentId " +
                        " AND SL.TO_ID IN (SELECT L.FROM_ID FROM STUDENT_LIKES L WHERE L.TO_ID=:studentId)",
                singletonMap("studentId", studentId), Long.class);
    }

    @Override
    public List<Long> findStudentIdsForMessageLikes(Long messageId) {
        return jdbcTemplate.queryForList("SELECT L.FROM_ID FROM MESSAGE_LIKES L WHERE L.TO_ID=:messageId",
                singletonMap("messageId", messageId), Long.class);
    }

    @Override
    public boolean saveLikeOrRemoveIfExists(Like like) {
        String likeSource = Optional.ofNullable(likeTypeToSourceTable(like.getType()))
                .orElseThrow(() -> new RuntimeException("Like type is null"));
        MapSqlParameterSource parameters = new MapSqlParameterSource("fromId", like.getFrom())
                .addValue("toId", like.getTo());
        if (!likeExists(likeSource, parameters)) {
            jdbcTemplate.update("INSERT INTO " + likeSource + " VALUES(:fromId,:toId,:timestamp)",
                    parameters.addValue("timestamp", Timestamp.valueOf(LocalDateTime.now())));
            return true;
        } else {
            jdbcTemplate.update("DELETE " + likeSource + " L WHERE L.FROM_ID=:fromId AND L.TO_ID=:toId",
                    parameters);
            return false;
        }
    }

    @Override
    public boolean checkIfLikeExists(Like like) {
        String likeSource = Optional.ofNullable(likeTypeToSourceTable(like.getType()))
                .orElseThrow(() -> new RuntimeException("Like type is null"));
        MapSqlParameterSource parameters = new MapSqlParameterSource("fromId", like.getFrom())
                .addValue("toId", like.getTo());
        return likeExists(likeSource, parameters);
    }

    private boolean likeExists(String likeSource, SqlParameterSource parameters) {
        return !jdbcTemplate.queryForList("SELECT L.FROM_ID FROM " + likeSource + " L WHERE L.FROM_ID=:fromId " +
                        " AND L.TO_ID=:toId",
                parameters, Long.class).isEmpty();
    }

    private String likeTypeToSourceTable(LikeType likeType) {
        if (LikeType.STUDENT_TO_STUDENT.equals(likeType)) {
            return "STUDENT_LIKES";
        }
        if (LikeType.STUDENT_TO_MESSAGE.equals(likeType)) {
            return "MESSAGE_LIKES";
        }
        return null;
    }

}
