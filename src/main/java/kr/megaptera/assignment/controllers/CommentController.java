package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> getComments(@RequestParam String postId) {
        List<CommentDto> commentsDtoByPostId = commentDtos.stream()
                                                .filter(i -> i.getPostId().equals(postId))
                                                .collect(Collectors.toList());
        return commentsDtoByPostId;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addComment(@RequestBody CommentDto commentDto, @RequestParam String postId) {
        commentDto.setId(getIncrementId().toString());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id, @RequestParam String postId, @RequestBody CommentDto updatedCommentDto) {
        CommentDto commentDto = commentDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        commentDtos.remove(commentDto);

        updatedCommentDto.setId(id);
        updatedCommentDto.setPostId(postId);
        commentDtos.add(updatedCommentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable String id) {
        CommentDto commentDto = commentDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        commentDtos.remove(commentDto);
    }

    private Long getIncrementId() {
        return ++newId;
    }

}
