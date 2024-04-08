package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
  private Long newId = 0L;

  private List<CommentDto> commentDtos = new ArrayList<>();

  @GetMapping
  public List<CommentDto> getCommentsByPostId(@RequestParam("postId") String postId) {
    return commentDtos.stream().filter(comment -> comment.getPostId().equals(postId)).toList();
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String createComment(@RequestParam("postId")String postId,@RequestBody String content){
    newId++;
    var comment = new CommentDto(newId,postId,content);
    commentDtos.add(comment);
    return "Complete!";
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void editComment(@PathVariable("id")Long id, @RequestParam String postId,@RequestBody CommentDto request){
      var findComment = commentDtos.stream().filter(comment -> comment.getId().equals(id)).findFirst().orElseThrow();
      findComment.setId(request.getId());
      findComment.setContent(request.getContent());
      findComment.setPostId(request.getPostId());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteComment(@PathVariable("id")Long id){
    var findComment = commentDtos.stream().filter(comment -> comment.getId().equals(id)).findFirst().orElseThrow();
    commentDtos.remove(findComment);
  }
}
