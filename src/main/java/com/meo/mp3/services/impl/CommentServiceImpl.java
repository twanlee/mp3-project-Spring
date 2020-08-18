package com.meo.mp3.services.impl;

import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.models.interactive.CommentResponse;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.repositories.CommentRepository;
import com.meo.mp3.services.CommentService;
import com.meo.mp3.services.UserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Override
    public List<Comment> findAllBySongId(Long songId) {
        return commentRepository.findAllBySong_IdOrderByCommentTimeDesc(songId);
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
        Comment comment = findById(id);
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public Comment createNewComment(Long song_Id, Long user_Id, String content) {
        User user = userService.findById(user_Id);
        Song song = songService.findById(song_Id);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setSong(song);
        comment.setContent(content);
        return commentRepository.save(comment);
    }
    @Override
    public CommentResponse convertToResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setCommentTime(comment.getCommentTime());
        commentResponse.setAvatarUrl(comment.getUser().getProfile().getAvatarUrl());
        commentResponse.setFullName(comment.getUser().getProfile().getFirstName() + ' '
                + comment.getUser().getProfile().getLastName());
        return commentResponse;
    }

    @Override
    public List<CommentResponse> convertToListResponse(List<Comment> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments){
            CommentResponse commentResponse = convertToResponse(comment);
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }
}
