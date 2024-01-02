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

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentList = new ArrayList<>();
        for (CommentDto commentDto : commentDtos) {
            if (commentDto.getPostId().equals(postId)) {
                commentList.add(commentDto);
            }
        }

        return commentList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam String postId,
                         @RequestBody CommentDto commentDto
    ) {
        commentDto.setId("" + getNewId());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id,
                       @RequestParam String postId,
                       @RequestBody CommentDto commentDto
    ) {
        for (int i = 0; i < commentDtos.size(); i++) {
            if (commentDtos.get(i).getPostId().equals(postId) &&
                    commentDtos.get(i).getId().equals(id)
            ) {
                commentDtos.set(i, commentDto);
                break;
            }
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,
                       @RequestParam String postId
    ) {
        for (int i = 0; i < commentDtos.size(); i++) {
            if (commentDtos.get(i).getPostId().equals(postId) &&
                    commentDtos.get(i).getId().equals(id)
            ) {
                commentDtos.remove(i);
                break;
            }
        }
    }

    public Long getNewId() {
        return newId++;
    }
}
