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
    public List<PostDto> list(){
        return postDtos;
    }
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id){
        return postDtos.stream().filter(i -> i.getId().equals(id))
            .findFirst().get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto reqBody){
        reqBody.setId(++newId);
        postDtos.add(reqBody);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modified(@RequestBody PostDto reqBody){
        postDtos.forEach(i->{
                if(i.getId().equals(reqBody.getId())){
                    i.setTitle(reqBody.getTitle());
                    i.setContent(reqBody.getContent());
                }
            });
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        PostDto deleteDto = postDtos.stream()
            .filter(i -> i.getId().equals(id))
            .findFirst()
            .get();

        postDtos.remove(deleteDto);
    }

}
