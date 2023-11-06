package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
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

@RequestMapping("/posts")
@RestController
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping()
    public List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable("id") String id) {
        PostDto foundPost = getPostDtoById(id);
        return foundPost;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postDto) {
        PostDto newPostDto = new PostDto(String.valueOf(newId++), postDto.getTitle(), postDto.getContent());
        postDtos.add(newPostDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        PostDto foundPost = getPostDtoById(id);
        foundPost.setTitle(postDto.getTitle());
        foundPost.setContent(postDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") String id) {
        PostDto foundPost = getPostDtoById(id);
        postDtos.remove(foundPost);
        CommentController.deleteAllCommentsByPostId(id);
    }

    public PostDto getPostDtoById(String id) {
        PostDto foundPost = postDtos.stream().filter(postDto -> postDto.getId().equals(id)).findFirst().get();
        return foundPost;
    }

}
