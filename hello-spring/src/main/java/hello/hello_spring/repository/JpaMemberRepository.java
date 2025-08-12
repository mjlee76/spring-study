package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 EntityManager로 모든게 동작
    // data-jpa를 설치하면 spring에서 알아서 EntityManager를 생성하므로
    private final EntityManager em;

    // 가져다 주입해서 사용하면 됨.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // JPA가 알아서 INSERT 쿼리를 넣어주고, member에 id까지 set해줌.(Pk만 가능)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입과 식별자만 넣어주면 찾아줌
        return Optional.ofNullable(member); // 값이 없을 수 있기 때문에 Optional로 감싸기
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPQL 사용(PK가 아니기 때문)
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL 사용(PK가 아니기 때문)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
