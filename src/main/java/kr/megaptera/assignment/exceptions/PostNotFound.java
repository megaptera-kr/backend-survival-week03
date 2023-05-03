package kr.megaptera.assignment.exceptions;

public class PostNotFound extends RuntimeException{
    public PostNotFound() {
        super("게시글을 찾을 수 없습니다.");
    }
}
