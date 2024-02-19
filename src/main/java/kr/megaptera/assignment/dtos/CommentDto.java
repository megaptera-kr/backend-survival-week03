package kr.megaptera.assignment.dtos;

public class CommentDto {

    private Long id;
    private Long postId;
    private String content;

    public CommentDto(Long id, Long postId, String content) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
