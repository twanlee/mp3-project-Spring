package com.meo.mp3.services.impl;

import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.repositories.CommentRepository;
import com.meo.mp3.services.ICommentService;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private SongService songService;
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
}
