package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    public static final String SUCCESS_MESSAGE = "Complete!";
    private final PostService postService;

    // TODO 삭제
    private Long newId = 0L;

    // TODO 삭제
//    private List<PostDTO> postDtos = new ArrayList<>();

    @GetMapping
    public List<PostDTO> list() {
        List<PostDTO> postDTOs = postService.list();
        return postDTOs;
    }

    @GetMapping("/{id}")
    public PostDTO get(@PathVariable Long id) {
        PostDTO postDTO = postService.get(id);
        return postDTO;
    }

    @PostMapping
    public String create(@RequestBody PostDTO postDTO) {
        postService.create(postDTO);

        return SUCCESS_MESSAGE;
    }

}
