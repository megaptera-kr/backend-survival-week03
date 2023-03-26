package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final ObjectMapper objectMapper;

    public CommentController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private Long newId = 0L;


    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CommentDto> getComments(
            @RequestParam("postId") String postId
    ) {
        //postId를 가진 comment 리스트 반환
        List<CommentDto> list = new ArrayList<>();

        for (CommentDto commentDto : commentDtos) {
            if (commentDto.getPostId().equals(postId)) {
                list.add(commentDto);
            }
        }
        return list;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String writeComment(
            @RequestParam("postId") String postId,
            @Valid @RequestBody String content
    ) {
        CommentDto commentDto = new CommentDto(autoIncreseId(), postId, content);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String updateComment(
            @PathVariable String id,
            @RequestParam("postId") String postId,
            @RequestBody String body
    ) throws JsonProcessingException {
        CommentDto updateComment = objectMapper.readValue(body, CommentDto.class);
        for (CommentDto commentDto : commentDtos) {
            if (commentDto.getId() == Long.parseLong(id)) {
                commentDto.setId(updateComment.getId());
                commentDto.setPostId(postId);
                commentDto.setContent(updateComment.getContent());
            }
        }
        return "";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteComment(
            @PathVariable String id,
            @RequestParam("postId") String postId
    ) {
        commentDtos.removeIf(commentDto -> commentDto.getId() == Long.parseLong(id)
                && commentDto.getPostId().equals(postId));
        return "";

    }

    private Long autoIncreseId() {
        return newId++;
    }
}
