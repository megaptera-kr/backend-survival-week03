package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.exception.NoSuchPostIdException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NoSuchPostIdException.class)
    public ResponseEntity<ResponseMessage> noSuchPostIdException(NoSuchPostIdException e) {
        log.info("exceptionHandler noSuchPostIdException");
        return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Getter
    public class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }
    }
}
