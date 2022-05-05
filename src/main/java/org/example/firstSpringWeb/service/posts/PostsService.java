package org.example.firstSpringWeb.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.firstSpringWeb.domain.posts.Posts;
import org.example.firstSpringWeb.domain.posts.PostsRepository;
import org.example.firstSpringWeb.web.dto.PostsResponseDto;
import org.example.firstSpringWeb.web.dto.PostsSaveRequestDto;
import org.example.firstSpringWeb.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }

}