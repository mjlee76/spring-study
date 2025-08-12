package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //스프링데이터JPA가 제공하지 않는 메서드는 직접 작성해야함
    //하지만 매우 간단(findBy + ~~ 등) -> 규칙이 있음
    //select m from Member where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
