package kr.megaptera.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
  private Long id;
  private String postId;
  private String content;
}
