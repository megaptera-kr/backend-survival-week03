package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private Long newId = 1L;

    private List<CommentDto> commentDtos = new ArrayList<>();

    @GetMapping
    public List<CommentDto> list(@RequestParam("postId") String postId) {

        List<CommentDto> reqCommentDtos = new ArrayList<>();

       commentDtos.stream()
               .filter(i -> i.getPostId().equals(postId))
               .forEach(j -> reqCommentDtos.add(j));
        

        return reqCommentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody PostDto reqCommentDto, @RequestParam("postId") String postId) {

        if (commentDtos.size() != 0) {
            commentDtos.stream()
                    .forEach(i -> {
                                if(Long.parseLong(i.getId()) + 1L > newId) { newId = Long.parseLong(i.getId()) + 1L;}
                            }
                    );
        }

        CommentDto newComment = new CommentDto(Long.toString(newId), postId, reqCommentDto.getContent());

        commentDtos.add(newComment);

        return "Complete";
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable("id") String id,
           @RequestParam("postId") String postId,
           @RequestBody CommentDto commentDto
    ) {

        int index = IntStream.range(0, commentDtos.size())
                .filter(i -> Objects.equals(commentDtos.get(i).getId(), id))
                .findFirst()
                .orElse(-1);


        if (index != -1 && Long.parseLong(postId) == Long.parseLong(commentDtos.get(index).getPostId())) {
            CommentDto updeatedComment = new CommentDto(id, postId, commentDto.getContent());

            commentDtos.set(index, updeatedComment);

        }

    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id, @RequestParam("postId") String postId) {

        int index = IntStream.range(0, commentDtos.size())
                .filter(i -> Objects.equals(commentDtos.get(i).getId(), id))
                .findFirst()
                .orElse(-1);

        if (index != -1 && Long.parseLong(postId) == Long.parseLong(commentDtos.get(index).getPostId())) {
            commentDtos.remove(index);

        }


    }

}
