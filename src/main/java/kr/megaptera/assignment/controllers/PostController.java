package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>(List.of(
            new PostDto("1","제목1", "내용1")
        )
    );

    @GetMapping
    public List<PostDto> list(){
        return postDtos;
    }
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id){

        return postDtos.get(Integer.parseInt(id) - 1);
    }

}
