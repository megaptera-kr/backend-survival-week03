package kr.megaptera.assignment.dtos;

public class PostDto {
    /**
     * 게시물 ID
     */
    private String id;
    /**
     * 게시물 제목
     */
    private String title;
    /**
     * 게시물 내용
     */
    private String content;

    public PostDto() {
    }

    public PostDto(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
