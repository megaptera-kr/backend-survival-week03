package kr.megaptera.assignment.dtos;

public class PostDto {

    private String id;

    private String title;

    private String content;

    // Default Constructor가 없으면 RequestBody를 DTO로 받기가 자동으로 안됨
    public PostDto() {
    }

    public PostDto(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id.toString();
    }
}
