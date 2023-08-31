package kr.megaptera.assignment.controllers;

import jakarta.websocket.server.PathParam;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {
    private Long newId = 0L;
    private List<PostDto> postDtos = new ArrayList<>();

    @CrossOrigin("http://localhost:8000")
    @GetMapping
    public List<PostDto> getList(){
        return postDtos;
    }

    @CrossOrigin("http://localhost:8000")
    @GetMapping("/{id}")
    public PostDto getDetail(@PathVariable String id){
//        PostDto postDto = postDtos.get(Integer.parseInt(id));
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        return postDto;
    }

    @CrossOrigin("http://localhost:8000")
    @PostMapping
    public PostDto create(@RequestBody PostDto postDto){
        postDto.setId(String.valueOf(newId++));
        postDtos.add(postDto);

        return postDto;
    }

    @CrossOrigin("http://localhost:8000")
    @DeleteMapping("{id}")
    public PostDto delete(@PathVariable String id){
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        postDtos.remove(postDto);
        return postDto;
    }

    @CrossOrigin("http://localhost:8000")
    @PutMapping("{id}")
    public PostDto modify(
            @PathVariable String id,
            @RequestBody PostDto postDto
    ){
        postDtos.remove(postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get());

        postDto.setId(String.valueOf(id));
        postDtos.add(postDto);

        return postDto;
    }


}
