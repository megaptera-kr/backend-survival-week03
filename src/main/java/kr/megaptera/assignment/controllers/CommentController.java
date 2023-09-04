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
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    /*
      댓글 조회
      @param postId
      @return List<CommentDto>
     */
    @GetMapping
    public List<CommentDto> getList(@RequestParam String postId){
        List<CommentDto> commentDtoList = commentDtos.stream()
                .filter(i -> i.getPostId().equals(postId))
                .toList();
        return  commentDtoList;
    }

    /*
      댓글 작성
      @param postId
      @param CommentDto
      @return String
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestParam String postId,
            @RequestBody CommentDto commentDto){

        commentDto.setId(createId());
        commentDto.setPostId(postId);

        commentDtos.add(commentDto);
        return "Complete!";
    }

    /*
      댓글 수정
      @param id
      @param postId
      @param CommentDto
    */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto commentDto){

        CommentDto tempCommentDto = commentDtos.stream()
                .filter(i -> i.getId().equals(id) && i.getPostId().equals(postId))
                .findFirst()
                .get();
        int idx = commentDtos.indexOf(tempCommentDto);
        commentDtos.set(idx, commentDto);
    }

    /*
      댓글 삭제
      @param id
      @param postId
    */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id,
            @RequestParam String postId){

        CommentDto commentDto = commentDtos.stream()
                .filter(i -> i.getId().equals(id) && i.getPostId().equals(postId))
                .findFirst()
                .get();
        commentDtos.remove(commentDto);
    }

    public String createId()
    {
        return String.valueOf(++newId);
    }
}
