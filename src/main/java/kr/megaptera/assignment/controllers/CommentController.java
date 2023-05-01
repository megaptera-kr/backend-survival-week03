package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/posts")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    private String makeId() {
        newId += 1;
        return newId.toString();
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> dtoList = commentDtos.stream()
                .filter(i -> i.getPostId().equals(postId))
                .toList();

        return dtoList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDto.setId(makeId());
        commentDto.setPostId(postId);

        commentDtos.add(commentDto);

        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) {
        commentDtos = commentDtos.stream()
                .map(item -> item.getPostId().equals(postId) && item.getId().equals(id)
                        ? commentDto
                        : item)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestParam String postId,
            @PathVariable String id
    ) {
        CommentDto commentDto = commentDtos.stream()
                .filter(item -> item.getPostId().equals(postId) && item.getId().equals(id))
                .findFirst()
                .get();

        commentDtos.remove(commentDto);
    }
}
