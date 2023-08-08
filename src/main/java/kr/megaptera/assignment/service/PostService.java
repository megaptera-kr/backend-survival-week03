package kr.megaptera.assignment.service;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDTO;
import kr.megaptera.assignment.exception.NoSuchPostIdException;
import kr.megaptera.assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
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

    public PostDTO get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoSuchPostIdException::new);
        return new PostDTO(post.getId(), post.getTitle(), post.getContent());
    }

    public void update(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoSuchPostIdException::new);
        post.update(postDTO.title(), postDTO.content());
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoSuchPostIdException::new);
        postRepository.delete(post);
    }
}
