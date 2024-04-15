package kr.megaptera.assignment.dtos;

public class CommentDto {
    private final String id;
    private final String postId;
    private String content;

    public CommentDto(String id, String postId, String content) {
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

    public void setContent(String content) {
        this.content = content;
    }
}
