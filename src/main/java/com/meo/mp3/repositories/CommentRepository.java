package com.meo.mp3.repositories;

import com.meo.mp3.models.interactive.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllBySong_IdOrderByCommentTimeDesc(Long songId);
    List<Comment> findAllByPlaylist_Id(Long playlistId);
}
