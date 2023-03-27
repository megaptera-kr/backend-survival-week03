package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    public Long incrementIdNumber(){
        return ++newId;
    }
    @GetMapping
    public List<CommentDto> list(@RequestParam("postId") String postId){
        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam("postId") String postId, @RequestBody CommentDto commentDto){
        Long newId = incrementIdNumber();
        commentDto.setId(newId.toString());
        commentDto.setPostId(postId);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody CommentDto commentDto, @PathVariable("id") String id, @RequestParam("postId") String postId)
    {
        CommentDto _commentDto = commentDtos.stream()
                .filter(cd->cd.getId().equals(id))
                .findFirst()
                .get();
        _commentDto.setId(commentDto.getId());
        _commentDto.setContent(commentDto.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("postId") String postId, @PathVariable String id)
    {
        CommentDto commentDto = commentDtos.stream()
                .filter(cd->cd.getId().equals(id))
                .findFirst()
                .get();

        commentDtos.remove(commentDto);
    }
}
