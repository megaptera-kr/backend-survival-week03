package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping()
    public List<PostDto> getPostsAll() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") String postId) {
        PostDto postDto = findById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        return savePost(postDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putPost(@PathVariable("id") String postId, @RequestBody PostDto postDto) {
        return updatePost(postId, postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String postId) {
        return removePost(postId);
    }



    protected PostDto findById(String postId) {
        for (PostDto dto : postDtos) {
            if (dto.getId().equals(postId)) {
                return dto;
            }
        }
        throw new NotFoundException();
    }

    protected String getNewId() {
        String id = String.valueOf(newId);
        newId++;
        return id;
    }

    protected int findIndex(String postId) {
        for (int i=0; i<postDtos.size(); i++) {
            if (postDtos.get(i).getId().equals(postId)) {
                return i;
            }
        }
        throw new NotFoundException();
    }

    protected ResponseEntity<String> savePost(PostDto postDto) {
        String id = getNewId();
        postDto.setId(id);
        postDtos.add(postDto);
        return new ResponseEntity<>("Complete!", HttpStatus.CREATED);
    }

    protected ResponseEntity<String> updatePost(String postId, PostDto postDto) {
        int index = findIndex(postId);
        postDtos.set(index, postDto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<String> removePost(String postId) {
        int index = findIndex(postId);
        postDtos.remove(index);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
