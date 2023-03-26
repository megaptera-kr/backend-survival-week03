package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final ObjectMapper objectMapper;
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    public PostController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostDto> getPosts() {
        return postDtos;
    }

    @GetMapping("/{id}")
    public String getPost(
            @PathVariable String id
    ) throws JsonProcessingException {
        for (PostDto postDto : postDtos) {
            if (postDto.getId() == Long.parseLong(id)) {
                return objectMapper.writeValueAsString(postDto);
            }
        }
        //못 찾으면 빈 문자열 출력 -> Exception 만들어서 못 찾았다고 처리해줘도 될듯
        return "";
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String writePost(
            @Valid @RequestBody PostDto dto
    ) throws JsonProcessingException {
        PostDto newPostDto = new PostDto(autoIncreseId(), dto.getTitle(), dto.getContent());
        postDtos.add(newPostDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String updatePost(
            @PathVariable String id,
            @RequestBody String body
    ) throws JsonProcessingException {
        PostDto updateDto = objectMapper.readValue(body, PostDto.class);
        //id로 dto 찾고, 값 덮어쓰기
        for (PostDto postDto : postDtos) {
            if (postDto.getId() == Long.parseLong(id)) {
                postDto.setId(updateDto.getId());
                postDto.setTitle(updateDto.getTitle());
                postDto.setContent(updateDto.getContent());
            }
        }
        return "";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deletePost(
            @PathVariable String id
    ) {
        postDtos.removeIf(postDto -> postDto.getId() == Long.parseLong(id));
        return "";
    }

    private Long autoIncreseId() {
        return newId++;
    }
}
