package com.meo.mp3.controllers;

import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.services.ICommentService;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentRestController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/{song_id}/save", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment createComment(@RequestBody Comment comment, @PathVariable Long song_id) {
        Song song = songService.findById(song_id);
        comment.setSong(song);
        return commentService.save(comment);
    }

    @GetMapping(value = "/api/{songId}/comments")
    public ResponseEntity<List<Comment>> getAllComment(@PathVariable  Long songId){
        List<Comment> comments = commentService.findAllBySongId(songId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
