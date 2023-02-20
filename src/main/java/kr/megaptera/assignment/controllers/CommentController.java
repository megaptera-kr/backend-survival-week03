package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    // 특정 게시물의 댓글 조회
    @GetMapping()
    public List<CommentDto> getComments(String postId){

        // 리턴할 댓글 DTO 리스트
        List<CommentDto> returnCommnetList = new ArrayList<>();

        // 전체 댓글에서 postId에 해당하는 댓글 모음
        for (CommentDto comment:
             commentDtos) {
            if (comment.getPostId().equals(postId)){
                returnCommnetList.add(comment);
            }
        }
        return returnCommnetList;
    }

    // 특정 게시물에 댓글 작성
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String writeComment(String postId,
                               @RequestBody CommentDto commentDto)
    {
        commentDto.setId(String.valueOf(newId)); // 댓글 아이디 할당
        commentDto.setPostId(postId); // 게시물 아이디 할당
        // 새로운 아이디 +1
        newId++;

        commentDtos.add(commentDto);

        return "complete!";
    }

    // 특정 게시물의 댓글 수정
    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateComment(String postId,
                              @PathVariable String commentId,
                              @RequestBody CommentDto commentDto){
        CommentDto updateTargetComment = null;

        // 업데이트 할 댓글 조회
        for (CommentDto dto : commentDtos) {
            if (dto.getId().equals(commentId)) {
                updateTargetComment = dto;
            }
        }
        // 댓글 내용 업데이트
        updateTargetComment.setContent(commentDto.getContent());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(String postId,
                              @PathVariable String commentId)
    {
        CommentDto deletTargetComment = null;

        // 삭제 할 댓글 조회
        for (CommentDto dto : commentDtos) {
            if (dto.getId().equals(commentId)) {
                deletTargetComment = dto;
            }
        }

        commentDtos.remove(deletTargetComment);
    }
}
