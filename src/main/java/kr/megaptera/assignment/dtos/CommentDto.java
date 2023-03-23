package kr.megaptera.assignment.dtos;


public class CommentDto {
    private String id;
    private String postId;
    private String content;

    public CommentDto() {
    }

    public CommentDto(String id, String postId, String content) {
        this.id = id;
        this.postId = postId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            new IllegalArgumentException("댓글 ID가 없습니다..");
        }
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        if (postId == null || postId.isEmpty()) {
            new IllegalArgumentException("게시물 ID가 없습니다..");
        }
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            new IllegalArgumentException("내용이 없습니다.");
        }
        this.content = content;
    }
}
