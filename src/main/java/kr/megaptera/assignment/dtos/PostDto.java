package kr.megaptera.assignment.dtos;

public class PostDto {

    private String id;
    private String title;
    private String content;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void addNewId(String id) {
        this.id = id;
    }
}
