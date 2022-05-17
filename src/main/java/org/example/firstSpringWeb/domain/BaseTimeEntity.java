package org.example.firstSpringWeb.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA entity 클래스들이 이 클래스를 상속할 경우 필드(createdDate, modifiedDate)도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate //entity 생성 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //entity 변경 시간 자동 저장
    private LocalDateTime modifiedDate;
}
