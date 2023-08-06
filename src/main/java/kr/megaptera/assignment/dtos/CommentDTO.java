package kr.megaptera.assignment.dtos;

public record CommentDTO(Long id, Long postId, String content) {
    public static CommentDTO of(String content) {
        return new CommentDTO(null, null, content);
    }

    public static CommentDTO of(Long id, String content) {
        return new CommentDTO(id, null, content);
    }
}
