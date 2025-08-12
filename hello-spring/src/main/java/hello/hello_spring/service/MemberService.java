package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /*// 직접 메서드 호출 시간 찍어보는 방법
    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }*/

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원X
        memberRepository.findByName(member.getName()) // -> Optional타입
                .ifPresent(m -> {  //Optional이라 null이 없음 -> ifNull 사용불가
                                            //ifPresent(값이 있다면)으로 대체
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMemebers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
