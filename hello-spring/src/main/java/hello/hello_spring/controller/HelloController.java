package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // 앞은 key, 뒤는 value(값)
        return "hello"; //의미: Spring(viewResolver)이 templates에서 hello를 찾아서 Thymeleaf 템플릿 엔진 처리(렌더링)
    }
}
