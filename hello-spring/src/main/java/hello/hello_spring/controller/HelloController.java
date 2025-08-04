package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // 앞은 key, 뒤는 value(값)
        return "hello"; //의미: Spring(viewResolver)이 templates에서 hello를 찾아서 Thymeleaf 템플릿 엔진 처리(렌더링)
    }

    // MVC와 템플릿 엔진 방식
    @GetMapping("hello-mvc")
    public String hellloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 -> 기초: 값전달
    @GetMapping("hello-string")
    @ResponseBody //의미: http프로토콜의 body부분에 return값을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //이 데이터를 화면에 그대로 내려서 보여줌.(html 불필요)
    }

    // API 방식 -> 데이터(json) 전달 (여기에선 hello 객체 만들어 전달)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
