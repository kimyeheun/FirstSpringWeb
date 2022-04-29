package org.example.firstSpringWeb.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { //<Entity 클래스, pk 타입>
}
