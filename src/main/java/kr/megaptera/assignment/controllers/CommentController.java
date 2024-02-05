package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping()
    public List<CommentDto> getComments(@RequestParam Long postId) {
        return this.commentDtos.stream()
                .filter(commentDto -> commentDto.getPostId().equals(postId))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam String postId, @RequestBody CommentDto commentDto) throws Exception {
        commentDto.setId(++newId);
        commentDto.setPostId(Long.valueOf(postId));
        this.commentDtos.add(commentDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(commentDtos);

        System.out.println(json);

        return "Complete!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestParam Long postId, @RequestBody CommentDto commentDto) {
        CommentDto targetCommentDto = this.commentDtos.stream()
                .filter(c -> c.getId().equals(id) && c.getPostId().equals(postId))
                .findFirst()
                .orElse(null);

        if (targetCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        targetCommentDto.setContent(commentDto.getContent());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id, @RequestParam Long postId) {
        CommentDto targetCommentDto = this.commentDtos.stream()
                .filter(c -> c.getId().equals(id) && c.getPostId().equals(postId))
                .findFirst()
                .orElse(null);

        if (targetCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        this.commentDtos.remove(targetCommentDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
