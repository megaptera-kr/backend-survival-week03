package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> list(
            @RequestParam String postId
    ) {
        List<CommentDto> returnList = targetComments(postId, commentDtos);
        return returnList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String commentCreate(
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDtos.add(new CommentDto(postId, String.valueOf(++newId), commentDto.getContent()));
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editComment(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto editDto
    ) {
        for (CommentDto c : commentDtos) {
            if (c.getId().equals(id) && c.getPostId().equals(postId)) {
                c.setContent(editDto.getContent());
                c.setId(editDto.getId());
                break;
            }
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id
    ) {
        for (CommentDto c : commentDtos) {
            if (c.getId().equals(id)) {
                commentDtos.remove(c);
                break;
            }
        }
    }

    public List<CommentDto> targetComments(String postId, List<CommentDto> commentDtos) {
        List<CommentDto> targetComments = new ArrayList<>();
        for (CommentDto c : commentDtos) {
            if (c.getPostId().equals(postId)) {
                targetComments.add(c);
            }
        }
        return targetComments;
    }

    public int findIdx(String id) {
        OptionalInt idx = IntStream.range(0, commentDtos.size())
                .filter(i -> id.equals(commentDtos.get(i).getId()))
                .findFirst();

        if (!idx.isPresent()) {
            throw new IllegalArgumentException("No Content");
        }
        return idx.getAsInt();
    }
}
