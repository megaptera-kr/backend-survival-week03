package kr.megaptera.assignment.dtos;

public class UpdateCommentRequest {

    private String id;
    private String postId;
    private String content;

    public UpdateCommentRequest() {
    }

    public UpdateCommentRequest(String id, String postId, String content) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }
}
