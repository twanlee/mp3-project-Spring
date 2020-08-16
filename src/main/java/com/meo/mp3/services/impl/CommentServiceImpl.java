package com.meo.mp3.services.impl;

import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.repositories.CommentRepository;
import com.meo.mp3.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> findAllBySongId(Long songId) {
        return commentRepository.findAllBySong_Id(songId);
    }

    @Override
    public List<Comment> findAllByPlaylistId(Long playlistId) {
        return commentRepository.findAllByPlaylist_Id(playlistId);
    }

    @Override
    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment save(Comment model) {
        return commentRepository.save(model);
    }

    @Override
    public Comment delete(Long id) {
        return null;
    }
}
