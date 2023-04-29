package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping()
    public List<CommentDto> getComment(@RequestParam("postId") String postId) {
        return findAllByPostId(postId);
    }

    @PostMapping()
    public ResponseEntity<String> createComment(@RequestParam("postId") String postId,
                                                @RequestBody CommentDto commentDto) {
        return saveComment(postId, commentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putComment(@RequestParam("postId") String postId,
                                             @PathVariable("id") String id,
                                             @RequestBody CommentDto commentDto) {
        return updateComment(postId, id, commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@RequestParam("postId") String postId,
                                                @PathVariable("id") String id) {
        return removeComment(postId, id);
    }


    protected List<CommentDto> findAllByPostId(String postId) {
        List<CommentDto> findCommentDtoList = new ArrayList<>();
        for (CommentDto commentDto : commentDtos) {
            if (commentDto.getPostId().equals(postId)) {
                findCommentDtoList.add(commentDto);
            }
        }
        return findCommentDtoList;
    }

    protected String getNewId() {
        String id = String.valueOf(newId);
        newId++;
        return id;
    }

    protected int findIndex(String commentId) {
        for (int i=0; i<commentDtos.size(); i++) {
            if (commentDtos.get(i).getId().equals(commentId)) {
                return i;
            }
        }
        throw new NotFoundException();
    }

    protected ResponseEntity<String> saveComment(String postId, CommentDto commentDto) {
        String id = getNewId();
        commentDto.setId(id);
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return new ResponseEntity<>("Complete!", HttpStatus.CREATED);
    }

    protected ResponseEntity<String> updateComment(String postId, String id, CommentDto commentDto) {
        int index = findIndex(id);
        commentDto.setPostId(postId);
        commentDtos.set(index, commentDto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<String> removeComment(String postId, String id) {
        int index = findIndex(id);
        commentDtos.remove(index);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
