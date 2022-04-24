package org.example.firstSpringWeb.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);  //assertThat()검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getAmount()).isEqualTo(amount); //isEqualTo() assertThat()의 인자와 같은지 확인하는 메소드
    }

}
