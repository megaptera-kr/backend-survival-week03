package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDto> list() {
        // 전체 리스트 리턴
        System.out.println("리스트 : " + postDtos.toString());
        if (postDtos.size() == 0) {
            return null;
        }
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detailPost(@PathVariable String id) {
        //id에 해당 하는 값 찾아서 리턴
        if (postDtos.size() == 0) {
            return null;
        }
        for (PostDto dto : postDtos) {
            if (dto.getId().equals(id)) {
                return dto;
            }
        }
        return null;
    }

    @PostMapping
    public void createPost(@RequestBody PostDto postDto) {
        // System.out.println("포스트매핑 객체 받아옴?" + postDto.toString());
        newId = newId + 1L;
        postDto.setId(newId.toString());
        postDtos.add(postDto);
    }

    @PutMapping("/{id}")
    public void updatePost(
            @PathVariable String id,
            @RequestBody PostDto postDto
    ) {
        for (PostDto dto : postDtos) {
            if (dto.getId().equals(id)) {
                dto.setTitle(postDto.getTitle());
                dto.setContent(postDto.getContent());
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable String id
    ) {
        if (postDtos.size() == 1) {
            postDtos.remove(0);
        }
        for (PostDto dto : postDtos) {
            if (dto.getId().equals(id)) {
                postDtos.remove(dto);
            }
        }
    }
}
