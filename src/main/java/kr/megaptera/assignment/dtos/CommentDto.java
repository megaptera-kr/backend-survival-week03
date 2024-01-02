package kr.megaptera.assignment.dtos;

public class CommentDto {

    // id
    private String id;
    // postId
    private String postId;
    // content
    private String content;

    // JavaBeans convention : 기본 생성자
    public CommentDto() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
