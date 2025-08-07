package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller  // Spring 컨테이너에 컨트롤러가 담기게 됨
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 생성자 주입: Spring 컨테이너에 있는 memberService를 가져와서 자동으로 연결해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
