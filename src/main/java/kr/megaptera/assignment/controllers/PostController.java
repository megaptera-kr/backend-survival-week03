package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.Service.PostService;
import kr.megaptera.assignment.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 조회
     * @param
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostList() {
        return postService.getPostList();
    }

    /**
     * 게시글 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable String id) {
        return postService.getPost(id);
    }

    /**
     * 게시글 생성
     *
     * @param postDto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    /**
     * 게시글 수정
     * @param id
     * @param postDto
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id,
                           @RequestBody PostDto postDto) {
        postService.updatePost(id, postDto);
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }

}
