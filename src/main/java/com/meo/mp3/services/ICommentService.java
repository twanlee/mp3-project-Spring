package com.meo.mp3.services;

import com.meo.mp3.models.interactive.Comment;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    List<Comment> findAllBySongId(Long songId);
    List<Comment> findAllByPlaylistId(Long playlistId);
    Comment createNewComment(Long songId, Long userId, String content);
}
