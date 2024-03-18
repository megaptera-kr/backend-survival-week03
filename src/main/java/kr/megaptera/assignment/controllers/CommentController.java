package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();

    private String generateId() {
        newId += 1;
        return newId.toString();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CommentDto commentDto,
                         @RequestParam String postId) {
        commentDto.setId(generateId());
        commentDto.setPostId(postId);

        commentDtos.add(commentDto);

        return "Complete!";
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtoList = commentDtos.stream()
                .filter(i -> i.getId().equals(postId))
                .toList();
        return commentDtoList;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam String postId,
                       @PathVariable String id,
                       @RequestBody CommentDto commentDto) {

        commentDtos = commentDtos.stream()
                .map(i -> i.getPostId().equals(postId) && i.getId().equals(id) ? commentDto : i)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String postId,
                       @PathVariable String id) {

        CommentDto commentDto = commentDtos.stream()
                .filter(i -> i.getPostId().equals(postId) && i.getId().equals(id))
                .findFirst()
                .get();

        commentDtos.remove(commentDto);
    }
}
