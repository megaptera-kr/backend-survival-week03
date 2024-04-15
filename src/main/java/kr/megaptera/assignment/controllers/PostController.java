package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private final List<PostDto> postDtos = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> list(){
        return postDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto showPost(@PathVariable String id){
        Optional<PostDto> optionalPostDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if(optionalPostDto.isEmpty()){
            throwPostNotFoundException(id);
        }
        return optionalPostDto.get();
    }

    private static void throwPostNotFoundException(String id) {
        throw new IllegalArgumentException("The post of id "
                + id + "does not exists");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto){
        PostDto newPost = new PostDto((newId++).toString(), postDto.getTitle(), postDto.getContent());
        postDtos.add(newPost);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(@PathVariable String id,
                     @RequestBody PostDto newPostDto){
        Optional<PostDto> postToEdit = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
        if(postToEdit.isEmpty()){
            throwPostNotFoundException(id);
        }

        postToEdit.get().setTitle(newPostDto.getTitle());
        postToEdit.get().setContent(newPostDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        OptionalInt postIdxToDelete = IntStream.range(0, postDtos.size())
                .filter(i -> id.equals(postDtos.get(i).getId()))
                .findFirst();

        if(postIdxToDelete.isEmpty()){
            throwPostNotFoundException(id);
        }
        postDtos.remove(postIdxToDelete.getAsInt());
    }
}
