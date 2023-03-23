package kr.megaptera.assignment.dtos;

public class PostDto {

    private String id;
    private String title;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("게시물 ID가 없습니다.");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목이 없습니다");
        }
        this.title = title;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용이 없습니다");
        }
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
