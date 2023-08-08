package kr.megaptera.assignment.dtos;

public record PostDTO(Long id, String title, String content) {
    public static PostDTO of(String title, String content) {
        return new PostDTO(null, title, content);
    }
}
