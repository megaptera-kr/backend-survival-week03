package kr.megaptera.assignment.dtos;

public class PostDto {

    // id
    private String id;
    // title
    private String title;
    // content
    private String content;

    // JavaBeans convention : 기본 생성자
    public PostDto() {
    }

    // id를 제외한 생성자
    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
    }

    public void setTitle(String title) {
    }

    public void setContent(String content) {
    }
}
