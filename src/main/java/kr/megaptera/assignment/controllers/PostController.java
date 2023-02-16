package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.AddPostRequestDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.utils.ResponseUtil;
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
import java.util.OptionalInt;
import java.util.stream.IntStream;

@CrossOrigin(value = "http://localhost:8000")
@RequestMapping("/posts")
@RestController
public class PostController {
  private Long newId = 0L;

  private List<PostDto> postDtos = new ArrayList<>();

  @GetMapping()
  public List<PostDto> getPosts() {
    return postDtos;
  }

  @GetMapping("/{id}")
  public PostDto getPostDatail(@PathVariable String id) {
    int foundIdx = findIdxById(id);
    return postDtos.get(foundIdx);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public String addPost(@RequestBody AddPostRequestDto addPostRequestDto) {
    PostDto postDto = new PostDto(String.valueOf(newId++), addPostRequestDto.getTitle(),
      addPostRequestDto.getContent());
    postDtos.add(postDto);
    return ResponseUtil.COMPLETE;
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void editPost(@PathVariable String id, @RequestBody PostDto postDto) {
    int foundIdx = findIdxById(id);
    postDtos.set(foundIdx, new PostDto(postDto.getId(), postDto.getTitle(), postDto.getContent()));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable String id) {
    int foundIdx = findIdxById(id);
    postDtos.remove(foundIdx);
  }

  private int findIdxById(String id) {
    OptionalInt indexOpt = IntStream.range(0, postDtos.size())
                                    .filter(i -> id.equals(postDtos.get(i).getId())).findFirst();
    if (!indexOpt.isPresent()) {
      throw new IllegalArgumentException("don't have that item");
    }
    return indexOpt.getAsInt();
  }
}
