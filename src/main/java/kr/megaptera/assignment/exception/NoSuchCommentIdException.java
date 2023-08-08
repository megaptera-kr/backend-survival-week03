package kr.megaptera.assignment.exception;

public class NoSuchCommentIdException extends RuntimeException{
    public NoSuchCommentIdException() {
        super("해당 댓글은 존재하지 않습니다");
    }
}
