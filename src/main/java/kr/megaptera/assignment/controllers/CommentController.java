package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDTO;
import kr.megaptera.assignment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private Long newId = 0L;

    private List<CommentDTO> commentDtos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<CommentDTO>> list(@RequestParam Long postId) {
        List<CommentDTO> commentDTOs = commentService.list(postId);
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }
}
