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

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // JavaBeans convention : setter
    // setter가 없으면 boot에서 Json String을 Deserialization할 때 필드 값을 넣어주지 못해 필드 값이 null이 된다.
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
