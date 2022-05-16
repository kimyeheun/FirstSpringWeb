package org.example.firstSpringWeb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";  //앞 경로와 뒤의 확장자는 자동 추가되어 View Resolver가 처리한다.
    }
}
