package kr.megaptera.assignment.controllers;

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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    @GetMapping()
    public List<PostDto> list(){
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id){
        PostDto a = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().get();

        PostDto postDto = new PostDto(a.getId(), a.getTitle(), a.getContent());

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto){
        postDto.setId(increaseId());

        postDtos.add(postDto);

        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody PostDto postDto){

        postDtos = postDtos.stream()
                .map(i -> i.getId().equals(id) ? postDto : i)
                .collect(Collectors.toList());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().get();

        postDtos.remove(postDto);

    }

    // 게시물의 아이디 값 1 씩 증가
    public String increaseId(){
        newId++;
        return newId.toString();
    }

}
