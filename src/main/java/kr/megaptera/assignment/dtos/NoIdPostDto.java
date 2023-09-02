package kr.megaptera.assignment.dtos;

public class NoIdPostDto {
    private String title;
    private String content;

    public NoIdPostDto() {
    }

    public NoIdPostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
