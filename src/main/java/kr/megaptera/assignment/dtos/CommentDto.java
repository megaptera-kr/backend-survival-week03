package kr.megaptera.assignment.dtos;

public class CommentDto {

    private String id;
    private String postId;
    private String content;

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public void addNewId(String postId, String id) {
        this.postId = postId;
        this.id = id;
    }
}