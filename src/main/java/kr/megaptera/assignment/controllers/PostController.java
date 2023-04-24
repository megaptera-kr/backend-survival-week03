package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts() {
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        Optional<PostDto> postDtoOptional = postDtos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (postDtoOptional.isPresent()) {
            return ResponseEntity.ok(postDtoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        postDto.setId(String.valueOf(++newId));
        postDtos.add(postDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Complete!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable String id, @RequestBody PostDto updatePostDto) {
        Optional<PostDto> postDtoOptional = postDtos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if(postDtoOptional.isPresent()) {
            PostDto postDto = postDtoOptional.get();
            postDto.setTitle(updatePostDto.getTitle());
            postDto.setContent(updatePostDto.getContent());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePost(@PathVariable String id) {
        Optional<PostDto> postDtoOptional = postDtos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if(postDtoOptional.isPresent()){
            newId--;
            postDtos.remove(postDtoOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
