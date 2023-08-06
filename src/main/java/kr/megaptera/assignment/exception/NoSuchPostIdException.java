package kr.megaptera.assignment.exception;

public class NoSuchPostIdException extends RuntimeException{
    public NoSuchPostIdException() {
        super("해당 게시글은 존재하지 않습니다");
    }
}
