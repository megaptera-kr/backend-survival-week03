package kr.megaptera.assignment.dtos;

public class PostDto {
    private Long id;
    private String title;
    private String content;

    public PostDto(
            Long id,
            String title,
            String content
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    // setter
    public void setPost(
            Long id,
            String title,
            String content
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
