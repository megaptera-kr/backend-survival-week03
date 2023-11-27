package kr.megaptera.assignment.dtos;

public class CommentDto {
    private long id;
    private String postId;
    private String content;

    public CommentDto() {
    }

    public CommentDto(long id, String postId, String content) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
