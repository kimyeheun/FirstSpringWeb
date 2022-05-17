package org.example.firstSpringWeb.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { //<Entity 클래스, pk 타입>
    @Query("SELECT p FROM Posts p ORDER BY p.id Desc")
    List<Posts> findAllDesc();
}
