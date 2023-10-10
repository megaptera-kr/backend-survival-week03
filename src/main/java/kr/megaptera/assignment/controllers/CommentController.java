package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 0L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId){
        List<CommentDto> result = new ArrayList<>();

        commentDtos.stream()
            .forEach(i->{
                if(i.getPostId().equals(postId)){
                    result.add(i);
                }
            });

        System.out.println(postId);

        return result;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
        @RequestParam String postId
        ,@RequestBody CommentDto reqBody){

        commentDtos.add(new CommentDto(++newId,postId,reqBody.getContent()));

        return "Complete!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modified(
        @PathVariable String id,
        @RequestParam String postId,
        @RequestBody CommentDto reqBody
    ){
        commentDtos.forEach(i->{
            if (i.getId() == Long.parseLong(id)) {
                i.setId(Long.parseLong(id));
                i.setContent(reqBody.getContent());
            }
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable String id,
        @RequestParam String postId
    ){
        CommentDto commentDto = commentDtos.stream()
            .filter(i->i.getId() == Long.parseLong(id))
            .findFirst()
            .get();
        commentDtos.remove(commentDto);
    }
}
