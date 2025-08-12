package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 이게 있으면 DB를 테스트 전으로 롤백해줌 -> 테스트하며 들어온 데이터를 삭제해줌, @AfterEach와 비슷
               // 다음 테스트를 바로 진행할 수 있음
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService; // SpringBoot 테스트이므로 @Autowired를 사용해서 주입
    @Autowired MemberRepository memberRepository;

    /*@AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }*/ // @Transactional 때문에 필요없음

    @Test
    //@Commit // Transactional 무시하고 DB에 들어갔는지 확인하고 싶을 때 사용
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //람다 방식 사용: 의미: memberService.join(member2)이걸 실행하면
            //IllegalStateException.class 이게 터져야 한다. 라는 뜻

    }

    @Test
    void findMemebers() {
    }

    @Test
    void findOne() {
    }
}