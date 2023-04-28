package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private final List<PostDto> postDtos = new ArrayList<>();


    @GetMapping
    public List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        for (PostDto postDto : postDtos) {
            if (postDto.getId().equals(id)) {
                return ResponseEntity.ok(postDto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        postDto.setId(getNewId());
        postDtos.add(postDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postDto.getId())
                .toUri();
        return ResponseEntity.created(location).body("Complete!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable String id, @RequestBody PostDto postDto) {
        for (PostDto post : postDtos) {
            if (post.getId().equals(id)) {
                post.setTitle(postDto.getTitle());
                post.setContent(postDto.getContent());
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        for (PostDto post : postDtos) {
            if (post.getId().equals(id)) {
                postDtos.remove(post);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    private String getNewId() {
        return String.valueOf(++newId);
    }

}
