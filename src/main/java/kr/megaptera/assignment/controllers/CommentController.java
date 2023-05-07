package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    public String createId(){
        return String.valueOf(this.newId++);
    }
    /**
     * 댓글조회
     * @param postId
     * @return List<CommentDto>
     */
    @GetMapping
    public List<CommentDto> list(@RequestParam String postId){

        return commentDtos.stream()
                .filter(i -> i.getPostId().equals(postId))
                .toList();
    }

    /**
     * 댓글 추가
     * @param postId
     * @param commentDto
     * @return String
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String created(@RequestParam String postId, @RequestBody CommentDto commentDto){
        commentDto.setId(createId());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    /**
     * 댓글 수정
     * @param id
     * @param postId
     * @param commentDto
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestParam String postId,
                       @RequestBody CommentDto commentDto){
        commentDto.setPostId(postId);
        commentDtos = commentDtos.stream()
                .map(i ->i.getId().equals(id) ? commentDto : i)
                .collect(Collectors.toList());

    }

    /**
     * 댓글 삭제
     * @param id
     * @param postId
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,
                       @RequestParam String postId){
        CommentDto commentDto = commentDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        commentDtos.remove(commentDto);
    }


}
