package kr.megaptera.assignment.exceptions;

import kr.megaptera.assignment.common.CommonMessage;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() {
        super(CommonMessage.POST_NOT_FOUND);
    }
}
