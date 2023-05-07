package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>(List.of());

    public String createId(){
        return String.valueOf(this.newId++);
    }
    /**
     * 게시글 전체 조회
     * @return List<PostDto>
     */
    @GetMapping
    public List<PostDto> list(){
        return postDtos;
    }
    /**
     * 게시글 상세조회
     * @param id
     * @return PostDto
     */
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id){
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        return postDto;
    }
    /**
     * 게시글 추가
     * @param postDto
     * @return String
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String created(@RequestBody PostDto postDto){
        postDto.setId(createId());
        postDtos.add(postDto);
        return "Complete!";
    }
    /**
     * 게시글 수정
     * @param id
     * @param postDto
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody PostDto postDto){
        postDtos = postDtos.stream()
                .map(i -> i.getId().equals(id) ? postDto : i)
                .collect(Collectors.toList());
    }
    /**
     * 게시글 삭제
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        postDtos.remove(postDto);
    }




}
