package kr.megaptera.assignment.dtos;

public class CreateCommentRequest {

  private String postId;
  private String content;

  public CreateCommentRequest() {
  }

  public CreateCommentRequest(String postId, String content) {
    this.postId = postId;
    this.content = content;
  }

  public String getPostId() {
    return postId;
  }

  public String getContent() {
    return content;
  }
}
