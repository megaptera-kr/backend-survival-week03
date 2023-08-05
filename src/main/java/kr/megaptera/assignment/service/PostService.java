package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
