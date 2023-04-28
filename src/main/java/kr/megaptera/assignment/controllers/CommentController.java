package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private final List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> getComments(@RequestParam String postId) {
        return commentDtos.stream().filter(commentDto -> commentDto.getPostId().equals(postId)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        commentDto.setId(getNewId());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Complete!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable String id, @RequestParam String postId, @RequestBody CommentDto commentDto) {
        for (CommentDto comment : commentDtos) {
            if (comment.getId().equals(id) && comment.getPostId().equals(postId)) {
                comment.setContent(commentDto.getContent());
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id, @RequestParam String postId) {
        for (CommentDto commentDto : commentDtos) {
            if (commentDto.getId().equals(id) && commentDto.getPostId().equals(postId)) {
                commentDtos.remove(commentDto);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    private String getNewId() {
        return String.valueOf(++newId);
    }
}
