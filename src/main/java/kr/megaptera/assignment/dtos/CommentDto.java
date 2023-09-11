package kr.megaptera.assignment.dtos;

public class CommentDto {
    private String id;
    private String postid;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentDto(String id, String postid, String content) {
        this.id = id;
        this.postid = postid;
        this.content = content;
    }
}
