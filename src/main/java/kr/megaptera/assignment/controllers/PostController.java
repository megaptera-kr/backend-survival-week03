package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {
    private Long newId = 0L;

    private List<PostDto> postDtos = new ArrayList<>();

    /*
        게시글 전체 조회
        @return List<PostDto>
     */
    @GetMapping
    public List<PostDto> getList() {
        return postDtos;
    }

    /*
      게시글 상세 조회
      @param id
      @return PostDto
   */
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {

        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        return postDto;
    }

    /*
      게시글 작성
      @param PostDto
      @return String
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto postDto){
        postDto.setId(String.valueOf(createId()));
        postDtos.add(postDto);
        return "Complete!";
    }

    /*
      게시글 수정
      @param id
      @param PostDto
    */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable String id
            ,@RequestBody PostDto postDto){
        PostDto tempPostDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().get();
        int idx = postDtos.indexOf(tempPostDto);
        postDtos.set(idx,postDto);
    }

    /*
      게시글 삭제
      @param id
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

    public String createId()
    {
        return String.valueOf(++newId);
    }
}
