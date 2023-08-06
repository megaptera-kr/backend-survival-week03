package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void create(PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.title())
                .content(postDTO.content())
                .build();

        postRepository.save(post);
    }

    public List<PostDTO> list() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = posts.stream()
                .map(post -> new PostDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getContent())
                ).collect(Collectors.toList());

        return postDTOs;
    }
}
