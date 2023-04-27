package kr.megaptera.assignment.dtos;

public class CommentDto {

    private String id;

    private String postId;

    private String content;

    public CommentDto() {
    }

    public CommentDto(String id, String postId, String content) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }

    public CommentDto(String content) {
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public String getPostId() {
        return this.postId;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
