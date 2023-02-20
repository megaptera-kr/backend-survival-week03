package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 1L;

    private final ObjectMapper objectMapper;

    public PostController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private List<PostDto> postDtos = new ArrayList<>();


    @GetMapping
    public List<PostDto> list() {

        return postDtos;
    }


    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {

        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto reqPostDto) {

        if (postDtos.size() != 0) {
            postDtos.stream()
                    .forEach(i -> {
                                if(Long.parseLong(i.getId()) + 1L > newId) { newId = Long.parseLong(i.getId()) + 1L;}
                            }
                    );
        }

        PostDto newPost = new PostDto(Long.toString(newId), reqPostDto.getTitle(), reqPostDto.getContent());

        postDtos.add(newPost);

        return "Complete";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,
                          @RequestBody PostDto postDto) {

        int index = IntStream.range(0, postDtos.size())
                .filter(i -> Objects.equals(postDtos.get(i).getId(), id))
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            PostDto updeatedPost = new PostDto(id, postDto.getTitle(), postDto.getContent());

            postDtos.set(index, updeatedPost);
        }

    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {

        int index = IntStream.range(0, postDtos.size())
                .filter(i -> Objects.equals(postDtos.get(i).getId(), id))
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            postDtos.remove(index);
        }


    }

}
