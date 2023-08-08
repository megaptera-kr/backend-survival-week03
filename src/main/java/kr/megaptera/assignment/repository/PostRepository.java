package kr.megaptera.assignment.repository;

import kr.megaptera.assignment.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
