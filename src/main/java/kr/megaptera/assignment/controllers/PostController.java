package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity getPosts() {
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPost(@PathVariable long postId) {
        var post = findPost(postId);
        if (post == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity addPost(@RequestBody PostDto reqBody) {
        reqBody.setId(++newId);
        postDtos.add(reqBody);
        return ResponseEntity.status(HttpStatus.CREATED).body("Complete!");
    }

    @PutMapping("/{postId}")
    public ResponseEntity updatePost(@PathVariable long postId, @RequestBody PostDto reqBody) {
        var post = findPost(postId);
        if (post == null) {
            return ResponseEntity.badRequest().body(null);
        }

        post.setTitle(reqBody.getTitle());
        post.setContent(reqBody.getContent());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable long postId) {
        var post = findPost(postId);
        if (post == null) {
            return ResponseEntity.badRequest().body(null);
        }

        postDtos.remove(post);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    private PostDto findPost(long id) {
        for (var post : postDtos) {
            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }


}
