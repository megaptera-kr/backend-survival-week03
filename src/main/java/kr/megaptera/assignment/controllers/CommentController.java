package kr.megaptera.assignment.controllers;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentController {
    private Long newId = 0L;
    private List<CommentDto> commentDtos = new ArrayList<>();


    @GetMapping
    public List<CommentDto> getList(@RequestParam String postId){
        List<CommentDto> tmp_commentDtos = new ArrayList<>();
        if (!commentDtos.isEmpty()){
            for (CommentDto c : commentDtos){
                if (c.getPostid().equals(postId)){
                    tmp_commentDtos.add(c);
                }
            }
        }
        return tmp_commentDtos;
    }

    @PostMapping
    public String create(
            @RequestBody CommentDto commentDto,
            @RequestParam String postId
    ){
        commentDto.setId(String.valueOf(newId++));
        commentDto.setPostid(postId);
        commentDtos.add(commentDto);
        return "Complete!";
    }

    @PutMapping("{id}")
    public void modify(
            @RequestBody CommentDto commentDto,
            @PathVariable String id
    ){
        // search in commentDtos.
        for (CommentDto c : commentDtos){
            if (c.getId().equals(id)){
                commentDto.setId(c.getId());
                commentDtos.remove(c);
                commentDtos.add(commentDto);
            }
        }
    }

    // *** ERROR: fail occurs, when first try ***
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
//        1)
//        for (CommentDto c : commentDtos){
//            if (c.getId().equals(id)){
//                commentDtos.remove(c);
//            }
//        }
        CommentDto commentDto = commentDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
        commentDtos.remove(commentDto);
    }

}
