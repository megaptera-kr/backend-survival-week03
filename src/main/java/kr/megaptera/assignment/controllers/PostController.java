package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> listPosts( ) {
        return postDtos;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto detailPost(@PathVariable String id) {
        PostDto postDto = postDtos.get(Integer.parseInt(id));
        return postDto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto postDto)  {
        newId++;
        postDtos.add(new PostDto(Long.toString(newId),postDto.getTitle(), postDto.getContent()));
        return "Complete!";
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePost(@PathVariable String id, @RequestBody PostDto requestPostDto) {
        PostDto postDto = postDtos.get(Integer.parseInt(id));
        postDto.setTitle( requestPostDto.getTitle() );
        postDto.setContent( requestPostDto.getContent() );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) {
        postDtos.remove(Integer.parseInt(id));
    }
}
