package org.example.firstSpringWeb.web;

import lombok.RequiredArgsConstructor;
import org.example.firstSpringWeb.config.auth.LoginUser;
import org.example.firstSpringWeb.config.auth.dto.SessionUser;
import org.example.firstSpringWeb.domain.posts.PostsRepository;
import org.example.firstSpringWeb.service.PostsService;
import org.example.firstSpringWeb.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        //LoginUser 어노테이션 적용.
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); 이 코드를 어노테이션 적용 이후 제거.
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";  //앞 경로와 뒤의 확장자는 자동 추가되어 View Resolver가 처리한다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
