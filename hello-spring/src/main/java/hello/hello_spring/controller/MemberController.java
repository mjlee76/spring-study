package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller  // Spring 컨테이너에 컨트롤러가 담기게 됨
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 생성자 주입: Spring 컨테이너에 있는 memberService를 가져와서 자동으로 연결해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; // 홈화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMemebers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
