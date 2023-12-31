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

    @GetMapping()
    public List<CommentDto> getCommentList(@RequestParam String postId) {
        // TODO(DEBUG): Remove this line.
        System.out.println("getCommentList()");
        return this.commentDtos.stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        // TODO(DEBUG): Remove this line.
        System.out.println("createComment(" + postId + ", " + commentDto + ")");
        // 요청할 때 postId 프로퍼티를 입력해도 무시하고 새로운 id를 부여한다.
        System.out.println(commentDto.getPostId());
        commentDto.setId(String.valueOf(newId++));
        commentDto.setPostId(postId);
        this.commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(@PathVariable String id,
                              @RequestParam String postId,
                              @RequestBody CommentDto commentDto) {
        // TODO(DEBUG): Remove this line.
        System.out.println("updateComment(" + id + ", " + postId + ", " + commentDto + ")");
        // id로 필터링 한다.
        // postId는 사용 안 해도 상관없다.
        this.commentDtos.stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst()
                .ifPresent(comment -> {
                    comment.setId(id);
                    comment.setContent(commentDto.getContent());
                    comment.setPostId(postId);
                });

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable String id, @RequestParam String postId) {
        // TODO(DEBUG): Remove this line.
        System.out.println("deleteComment(" + id + ", " + postId + ")");
        // id로 필터링 한다.
        // postId는 사용 안 해도 상관없다.
        this.commentDtos.removeIf(comment -> comment.getId().equals(id));
    }
}
