package kr.megaptera.assignment.controllers;

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
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postList = new ArrayList<>();

    // 게시글 목록 조회
    @GetMapping()
    public List<PostDto> getPostsList(){
        return postList;
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public PostDto getPostDetails(@PathVariable String postId) {

        PostDto targetPost = null;

        // 업데이트 할 게시물 조회
        for (PostDto dto : postList) {
            if (dto.getId().equals(postId)) {
                targetPost = dto;
            }
        }

        return targetPost;
    }

    // 게시물 작성
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String writePost(@RequestBody PostDto postDto){

        // 아이디 할당
        postDto.setId(String.valueOf(newId));

        // 다음 아이디 플러스 1
        newId++;

        // 게시물 목록에 추가
        postList.add(postDto);

        return "Complete!";
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String postId,
                           @RequestBody PostDto postDto) {

        PostDto updateTargetPost = null;

        // 업데이트 할 게시물 조회
        for (PostDto dto : postList) {
            if (dto.getId().equals(postId)) {
                updateTargetPost = dto;
            }
        }
        // 수정 사항 업데이트
        updateTargetPost.setTitle(postDto.getTitle());
        updateTargetPost.setContent(postDto.getContent());
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String postId){
        PostDto deletTargetPost = null;
        // 삭제 할 게시물 조회
        for (PostDto dto : postList) {
            if (dto.getId().equals(postId)) {
                deletTargetPost = dto;
            }
        }
        // 게시물 삭제
        postList.remove(deletTargetPost);
    }
}
