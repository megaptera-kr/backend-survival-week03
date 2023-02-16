package kr.megaptera.assignment.dtos;

public class PostDto {
  private Long id;
  private String title;
  private String content;

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public PostDto() {
  }

  public PostDto(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }
}
