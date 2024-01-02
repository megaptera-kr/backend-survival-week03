package kr.megaptera.assignment.Service;

import kr.megaptera.assignment.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final List<PostDto> postDtos = new ArrayList<>();
    private Long newId = 0L;


    public String createPost(PostDto postDto) {
        log.info("postDto: {}", postDto);
        try {
            postDto.setId("" + getId());
            postDtos.add(postDto);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        }
        return "Completed!";
    }

    public PostDto getPost(String id) {

        return postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
    }

    public void updatePost(String id, PostDto postDto) {
        for (PostDto post : postDtos) {
            if (post.getId().equals(id)) {
                post.setTitle(postDto.getTitle());
                post.setContent(postDto.getContent());
            }
        }
    }

    public void deletePost(String id) {
        try {
            postDtos.removeIf(postDto -> postDto.getId().equals(id));
        } catch (Exception e) {
            String error = e.getMessage();
            throw new RuntimeException(error ,e);
        }

    }

    private String getId() {
        return String.valueOf(newId++);
    }

    public List<PostDto> getPostList() {
        return postDtos;
    }
}
