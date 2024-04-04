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
    @GetMapping
    public List<PostDto> getList(){
        return postDtos;
    }
    @GetMapping("/{id}")
    public PostDto getDetail(@PathVariable Long id){
      return postDtos.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostDto request){
        newId++;
        request.setId(newId);
        postDtos.add(request);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPost(@PathVariable Long id,@RequestBody PostDto request){
        var findPost = postDtos.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
        findPost = request;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id){
        var findPost = postDtos.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
        postDtos.remove(findPost);
    }


}
