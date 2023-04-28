package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> commentList(
            @RequestParam String postId
    ) {
        // 게시글 안의 댓글 보여주기
        System.out.println("commentList : " + commentDtos.toString());
        List<CommentDto> postComments = new ArrayList<>();
        for (CommentDto dto : commentDtos) {
            if (dto.getPostId().equals(postId)) {
                postComments.add(dto);
            }
        }
        return postComments;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        newId = newId + 1L;
        commentDto.setId(newId.toString());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyComent(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        for (CommentDto dto : commentDtos) {
            if (dto.getPostId().equals(postId) && dto.getId().equals(id)) {
                dto.setContent(commentDto.getContent());
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        if (commentDtos.size() == 1) {
            commentDtos.remove(0);
        }
        for (CommentDto dto : commentDtos) {
            if (dto.getPostId().equals(postId) && dto.getId().equals(id)) {
                commentDtos.remove(dto);
            }
        }
    }
}
