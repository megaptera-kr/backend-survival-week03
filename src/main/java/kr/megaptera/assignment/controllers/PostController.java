package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping()
    public List<PostDto> getPosts() {
        System.out.println("getPosts");
        return postDtos;
    }

    @PostMapping()
    public PostDto createPost(PostDto postDto) {
        System.out.println("createPost");
        postDto.setPost(
                newId++,
                postDto.getTitle(),
                postDto.getContent()
        );
        postDtos.add(postDto);
        return postDto;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostDto updatePost(
            @PathVariable Long id,
            PostDto postDto
    ) {
        System.out.println("updatePost");

        for (PostDto post : postDtos) {
            if (post.getId().equals(id)) {
                post.setPost(
                        postDto.getId(),
                        postDto.getTitle(),
                        postDto.getContent()
                );
                return post;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long id
    ) {
        System.out.println("deletePost");

        for (PostDto post : postDtos) {
            if (post.getId().equals(id)) {
                postDtos.remove(post);
                return;
            }
        }
    }
}
