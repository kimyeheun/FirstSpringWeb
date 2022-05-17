package org.example.firstSpringWeb.web;

import lombok.RequiredArgsConstructor;
import org.example.firstSpringWeb.domain.posts.PostsRepository;
import org.example.firstSpringWeb.service.PostsService;
import org.example.firstSpringWeb.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { //서버 템플릿 엔진에서 사용할 수 있는 객제를 저장할 수 있음음
        model.addAttribute("posts", postsService.findAllDesc());
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
