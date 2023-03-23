package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@RequestParam String postId) {
        List<CommentDto> comments = commentDtos.stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<String> createComments(@RequestParam String postId, @RequestBody CommentDto newCommentDto) {
        newCommentDto.setId(String.valueOf(++newId));
        newCommentDto.setPostId(postId);
        commentDtos.add(newCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Complete!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComments(@PathVariable String id, @RequestParam String postId, @RequestBody CommentDto updateCommentDto) {
        Optional<CommentDto> postIdOptional = commentDtos.stream()
                .filter(e -> e.getPostId().equals(postId))
                .findFirst();

        if (postIdOptional.isPresent()) {
            Optional<CommentDto> commentDtoOptional = commentDtos.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst();

            if (commentDtoOptional.isPresent()) {
                CommentDto commentDto = commentDtoOptional.get();
                commentDto.setContent(updateCommentDto.getContent());
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeComments(@PathVariable String id, @RequestParam String postId) {
        Optional<CommentDto> postIdOptional = commentDtos.stream()
                .filter(e -> e.getPostId().equals(postId))
                .findFirst();

        if (postIdOptional.isPresent()) {
            Optional<CommentDto> commentDtoOptional = commentDtos.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst();

            if (commentDtoOptional.isPresent()) {
                newId--;
                commentDtos.remove(commentDtoOptional.get());
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
