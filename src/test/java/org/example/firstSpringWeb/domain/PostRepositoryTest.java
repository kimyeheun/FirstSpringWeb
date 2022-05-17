package org.example.firstSpringWeb.domain;

import org.example.firstSpringWeb.domain.posts.Posts;
import org.example.firstSpringWeb.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //H2 데이터베이스 자동 실행
public class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After  //테스트 끝날 때 마다 cleanup 할거임
            //테스트 간의 데이터 침범을 막기 위해 사용
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()    //.save 테이블 posts에 insert/Update 쿼리를 실행
                                                //id 값이 있으면 Update, 없으면 insert 쿼리 실행
                        .title(title)
                        .content(content)
                        .author("yeheun004@gmail.com")
                        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();  //테이블 posts에 있는 모든 값 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate= " + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
