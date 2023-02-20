package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.AddCommentRequestDto;
import kr.megaptera.assignment.dtos.CommentDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@RestController
@CrossOrigin(value = "http://localhost:8000")
@RequestMapping("/comments")
public class CommentController {
  private Long newId = 0L;
  private List<CommentDto> commentDtos = new ArrayList<>();

  @GetMapping()
  List<CommentDto> getComments(@RequestParam String postId) {
    List<CommentDto> searchComments = new ArrayList<>();
    for (CommentDto commentDto :
      commentDtos) {
      if (postId.equals(commentDto.getPostId())) {
        searchComments.add(commentDto);
      }
    }
    return searchComments;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public String addComment(@RequestParam String postId,
                           @RequestBody AddCommentRequestDto addCommentRequestDto) {
    commentDtos.add(new CommentDto(String.valueOf(newId++),
      postId,
      addCommentRequestDto.getContent()));
    return ResponseUtil.COMPLETE;
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  public void editComment(@PathVariable String id,
                          @RequestParam String postId,
                          @RequestBody CommentDto editCommentDto) {
    int foundIdx = findIdx(id);
    commentDtos.set(foundIdx,
      new CommentDto(id, postId, editCommentDto.getContent()));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteComment(@PathVariable String id) {
    int foundIdx = findIdx(id);
    commentDtos.remove(foundIdx);
  }

  private int findIdx(String id) {
    OptionalInt indexOpt = IntStream.range(0, commentDtos.size())
                                    .filter(idx -> id.equals(commentDtos.get(idx).getId()))
                                    .findFirst();
    if (!indexOpt.isPresent()) {
      throw new IllegalArgumentException("don't have that item");
    }
    return indexOpt.getAsInt();
  }
}
