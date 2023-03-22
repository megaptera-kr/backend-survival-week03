package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity getComment(@RequestParam long postId) {
        var comments = getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity addComment(@RequestParam long postId, @RequestBody CommentDto reqBody){
        reqBody.setId(++newId);
        reqBody.setPostId(postId);
        commentDtos.add(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED).body("Complete!");
    }

    @PutMapping("{commentId}")
    public ResponseEntity updateComment(@PathVariable long commentId, @RequestBody CommentDto reqBody){
        var comment = getCommentsById(commentId);
        if(comment == null){
            return ResponseEntity.badRequest().body(null);
        }

        comment.setContent(reqBody.getContent());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity removeComment(@PathVariable long commentId){
        var comment = getCommentsById(commentId);
        if(comment == null){
            return ResponseEntity.badRequest().body(null);
        }

        commentDtos.remove(comment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    private List<CommentDto> getCommentsByPostId(long postId) {
        List<CommentDto> comments = new ArrayList<>();

        for (var comment : commentDtos) {
            if(comment.getPostId() == postId){
                comments.add(comment);
            }
        }

        return comments;
    }


    private CommentDto getCommentsById(long id) {

        for (var comment : commentDtos) {
            if(comment.getId() == id){
                return comment;
            }
        }

        return null;
    }



}
