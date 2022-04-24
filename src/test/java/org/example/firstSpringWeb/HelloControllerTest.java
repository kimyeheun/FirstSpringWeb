package org.example.firstSpringWeb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.example.firstSpringWeb.web.HelloController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) //SpringRunner라는 JUint에 없는 실행자를 테스트와 연결!
@WebMvcTest(controllers = HelloController.class) //web에 집중할 수 있는 어노테이션 (@Controller 등 사용 가능)
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 bean 주입
    private MockMvc mvc; //테스트의 본격적인 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  // /hello 주소로 http get 4444444444444444444444요청을 함
                .andExpect(status().isOk())  //http header의 status 검증 (200, 404)
                .andExpect(content().string(hello));  // hello 값을 리턴하는게 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                            .param("name", name)  //Api 테스트에 요청 파라미터를 설정한다. (String 값만 허용)
                            .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
