package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // 직접 스프링 컨테이너에 Bean 등록하는 방법
public class SpringConfig {

//    // Jdbc, JdbcTemplate 에서 사용
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    // JPA 에서 사용
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 스프링 데이터 JPA 에서 사용
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // 필요한 인자(리포지토리)-> 아래의 메서드(memberRepository())를 넣어줌.
                                                    // 스프링 데이터 JPA는 Bean등록 메서드가 필요없으므로
                                                    // 자동 생성된 구현체를 넣어준다.
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository(); // 인터페이스(MemberRepository)는 new가 안됨
//                                             // -> 구현체(MemoryMemberRepository)를 return해줌
//                                                // 메모리에만 데이터를 저장하던 임시 구현체
//        //return new JdbcMemberRepository(dataSource);      // 데이터베이스(H2 등)를 JDBC로 연결하여 실제 영속 저장하도록 변경
//        //return new JdbcTemplateMemberRepository(dataSource);  // 데이터베이스(H2 등)를 JDBC Template으로 연결하여 실제 영속 저장하도록 변경
//        //return new JpaMemberRepository(em);
//        // 스프링 데이터 JPA는 자동으로 레포지토리 인터페이스를 감지하고 구현체를 만들어서 빈으로 등록해 주기 때문에
          // 직접 Bean을 등록할 필요가 없음!
//    }
}
