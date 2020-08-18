package com.meo.mp3.controllers;

import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.models.interactive.CommentResponse;
import com.meo.mp3.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment/")
public class CommentRestController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{user_id}/{song_id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentResponse comment, @PathVariable Long song_id, @PathVariable Long user_id) {
        Comment cmt = commentService.createNewComment(song_id,user_id,comment.getContent());
        CommentResponse response = new CommentResponse();
        response.setId(cmt.getId());
        response.setContent(cmt.getContent());
        response.setCommentTime(cmt.getCommentTime());
        response.setFullName(cmt.getUser().getProfile().getFirstName() + ' '
                + cmt.getUser().getProfile().getLastName());
        response.setAvatarUrl(cmt.getUser().getProfile().getAvatarUrl());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/{songId}/list")
    public ResponseEntity<List<CommentResponse>> getAllComment(@PathVariable Long songId){
        List<Comment> comments = commentService.findAllBySongId(songId);
        List<CommentResponse> response = new ArrayList<>();
        for (Comment cmt : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse = this.commentService.convertToResponse(cmt);
            response.add(commentResponse);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{comment_id}/delete")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long comment_id) {
        commentService.delete(comment_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
