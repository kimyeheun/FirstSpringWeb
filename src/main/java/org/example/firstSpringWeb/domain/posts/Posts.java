package org.example.firstSpringWeb.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스이다. -> 절대!!!! Setter 메소드를 만들지 않는다!
        //DB 삽입 방법? <- 생성자(여기서는 @Builder 빌더 클래스)를 통해 값을 최운 후 DB에 삽입
public class Posts extends BaseTimeEntity{

    @Id //테이블의 pk 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 생성 규칙
    private Long id;  //Long 타입, Auto_increment 추천

    @Column(length = 500, nullable = false)  //테이블의 칼럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    //빌더 패턴 클래스 생성 (생성자 상단에 있으니까 생성자만 빌더에 포함)
                //<- 어느 필드에 어느 값을 채워야 할지 명확하게 인지 할 수 있다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
