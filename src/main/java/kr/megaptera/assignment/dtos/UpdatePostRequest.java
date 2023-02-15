package kr.megaptera.assignment.dtos;

public class UpdatePostRequest {

  private String id;
  private String title;
  private String content;

  public UpdatePostRequest() {
  }

  public UpdatePostRequest(String id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }
}
