package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    public Long incrementIdNumber(){
        return ++newId;
    }

    @GetMapping
    public List<PostDto> list(){
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id){

        PostDto postDto = postDtos.stream()
                .filter(pd->pd.getId().equals(id))
                .findFirst()
                .get();

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto){
        Long newId = incrementIdNumber();
        postDto.setId(newId.toString());
        postDtos.add(postDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PostDto postDto, @PathVariable String id){
        PostDto _postDto = postDtos.stream()
                .filter(pd->pd.getId().equals(id))
                .findFirst()
                .get();
        _postDto.setId(postDto.getId());
        _postDto.setTitle(postDto.getTitle());
        _postDto.setContent(postDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id)
    {
        PostDto postDto = postDtos.stream()
                .filter(pd->pd.getId().equals(id))
                .findFirst()
                .get();

        postDtos.remove(postDto);
    }
}
