package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();


    @GetMapping
    private List<CommentDto> getComments(@RequestParam("postId") String postId) {
        List<CommentDto> findComments = commentDtos
                .stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .toList();
        return findComments;
    }

}
