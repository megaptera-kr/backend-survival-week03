package kr.megaptera.assignment.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CommentDto implements Serializable {
    private Long id;
    private String postId;
    @NotNull
    private String content;

    public CommentDto() {
    }

    public CommentDto(Long id, String postId, String content) {
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
