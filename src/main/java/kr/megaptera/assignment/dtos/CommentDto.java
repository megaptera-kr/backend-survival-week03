package kr.megaptera.assignment.dtos;

public class CommentDto {
    private String postId;
    private String id;
    private String content;

    public CommentDto() {
    }

    public CommentDto(String postId, String commentId, String content) {
        this.postId = postId;
        this.id = commentId;
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String commentId) {
        this.id = commentId;
    }
}
