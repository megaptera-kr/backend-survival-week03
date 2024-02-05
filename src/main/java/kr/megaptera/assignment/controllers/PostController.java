package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
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
    public List<PostDto> getPosts() {
        return this.postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return this.postDtos.stream()
                .filter(postDto -> postDto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postDto) {
        postDto.setId(++newId);
        this.postDtos.add(postDto);

        return "Complete!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        PostDto targetPostDto = this.postDtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (targetPostDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        targetPostDto.setTitle(postDto.getTitle());
        targetPostDto.setContent(postDto.getContent());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        PostDto targetPostDto = this.postDtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (targetPostDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        this.postDtos.remove(targetPostDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
