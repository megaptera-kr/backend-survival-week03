package kr.megaptera.assignment.dtos;

public class CommentDto {
    private Long id;
    private Long postId;
    private String content;

    public CommentDto(
            Long id,
            Long postId,
            String content
    ) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public Long getPostId() {
        return this.postId;
    }

    public String getContent() {
        return this.content;
    }

    // setter
    public void setComment(
            Long id,
            Long postId,
            String content
    ) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }
}
