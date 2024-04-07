package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private final HashMap<Long, CommentDto> commentDtos = new HashMap<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> listComments(){
        return new ArrayList<>(commentDtos.values());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createAComment(@RequestParam String postId,
                                 @RequestBody CommentDto commentDto){
        CommentDto newComment = new CommentDto(newId.toString(), postId, commentDto.getContent());
        commentDtos.put(newId, newComment);
        newId++;
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editAComment(@PathVariable Long id,
                             @RequestParam String postId,
                             @RequestBody CommentDto comment) {
        CommentDto originalComment = commentDtos.get(id);
        if(!originalComment.getPostId().equals(postId)){
            throw new IllegalArgumentException("post ID Incorrect");
        }
        originalComment.setContent(comment.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAComment(@PathVariable Long id,
                               @RequestParam String postId) {
        CommentDto originalComment = commentDtos.get(id);
        if(!originalComment.getPostId().equals(postId)){
            throw new IllegalArgumentException("post ID Incorrect");
        }
        commentDtos.remove(id);
    }
}
