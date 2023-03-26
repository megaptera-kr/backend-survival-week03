package kr.megaptera.assignment.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class PostDto implements Serializable {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
