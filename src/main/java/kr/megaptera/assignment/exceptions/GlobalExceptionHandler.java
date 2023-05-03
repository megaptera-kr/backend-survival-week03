package kr.megaptera.assignment.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

}
