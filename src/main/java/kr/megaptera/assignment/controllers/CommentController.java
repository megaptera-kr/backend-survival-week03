package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping("/")
    public List<CommentDto> getComments(
            @RequestParam Long postId
    ) {
        System.out.println("getComments for postId:" + postId);
        return commentDtos.stream()
                .filter(commentDto -> commentDto.getPostId().equals(postId))
                .toList();
    }

    @PostMapping("/")
    public CommentDto createComment(
            @RequestParam Long postId,
            CommentDto commentDto
    ) {
        System.out.println("createComment for postId:" + postId);
        commentDto.setComment(
                newId++,
                postId,
                commentDto.getContent()
        );
        commentDtos.add(commentDto);
        return commentDto;
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(
            @PathVariable Long id,
            CommentDto commentDto
    ) {
        System.out.println("updateComment for id:" + id);

        for (CommentDto comment : commentDtos) {
            if (comment.getId().equals(id)) {
                comment.setComment(
                        commentDto.getId(),
                        commentDto.getPostId(),
                        commentDto.getContent()
                );
                return comment;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(
            @PathVariable Long id
    ) {
        System.out.println("deleteComment for id:" + id);

        for (CommentDto comment : commentDtos) {
            if (comment.getId().equals(id)) {
                commentDtos.remove(comment);
                return;
            }
        }
    }
}
