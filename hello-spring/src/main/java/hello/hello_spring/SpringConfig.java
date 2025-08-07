package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 직접 스프링 컨테이너에 Bean 등록하는 방법
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 필요한 인자(리포지토리)-> 아래의 메서드를 넣어줌.
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 인터페이스(MemberRepository)는 new가 안됨
                                             // -> 구현체(MemoryMemberRepository)를 return해줌
    }
}
