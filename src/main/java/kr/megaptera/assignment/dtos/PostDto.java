package kr.megaptera.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostDto {
    private Long id; // 게시물 아이디 값
    private String title; // 게시물 제목
    private String content; // 게시물 내용

    private boolean isDeleted; // 삭제 여부

    public PostDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @JsonIgnore()
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
