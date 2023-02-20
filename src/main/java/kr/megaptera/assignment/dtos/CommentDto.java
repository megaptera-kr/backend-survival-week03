package kr.megaptera.assignment.dtos;

public class CommentDto {
  private String id;
  private String postId;
  private String content;

  public String getId() {
    return id;
  }

  public String getPostId() {
    return postId;
  }

  public String getContent() {
    return content;
  }

  public CommentDto() {
  }

  public CommentDto(String id, String postId, String content) {
    this.id = id;
    this.postId = postId;
    this.content = content;
  }
}
