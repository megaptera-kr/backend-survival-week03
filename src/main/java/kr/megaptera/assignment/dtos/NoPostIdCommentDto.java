package kr.megaptera.assignment.dtos;

public class NoPostIdCommentDto {
    private String id;
    private String content;

    public NoPostIdCommentDto() {
    }

    public NoPostIdCommentDto(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
