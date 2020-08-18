package com.meo.mp3.services;

import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.models.interactive.CommentResponse;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<Comment> findAllBySongId(Long songId);
    List<Comment> findAllByPlaylistId(Long playlistId);
    Comment createNewComment(Long songId, Long userId, String content);
    CommentResponse convertToResponse(Comment comment);
    List<CommentResponse> convertToListResponse(List<Comment> comments);
}
