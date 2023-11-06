package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private static List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping()
    public List<CommentDto> getComments(@RequestParam("postId") String postId) {
        List<CommentDto> foundCommentDtos = commentDtos.stream().filter(commentDto -> commentDto.getPostId().equals(postId)).toList();
        return foundCommentDtos;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam("postId") String postId, @RequestBody CommentDto commentDto) {
        CommentDto newCommentDto = new CommentDto(String.valueOf(newId++), postId, commentDto.getContent());
        commentDtos.add(newCommentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable("id") String id, @RequestParam("postId") String postId, @RequestBody CommentDto commentDto) {
        CommentDto foundCommentDto = getFoundCommentDto(id, postId);
        foundCommentDto.setContent(commentDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("id") String id, @RequestParam("postId") String postId) {
        CommentDto foundCommentDto = getFoundCommentDto(id, postId);
        commentDtos.remove(foundCommentDto);
    }

    private CommentDto getFoundCommentDto(String id, String postId) {
        CommentDto foundCommentDto = commentDtos.stream().filter(commentDtoExist -> commentDtoExist.getPostId().equals(postId) && commentDtoExist.getId().equals(id)).findFirst().get();
        return foundCommentDto;
    }

    public static void deleteAllCommentsByPostId(String postId) {
        commentDtos.removeIf(commentDto -> commentDto.getPostId().equals(postId));
    }

}
