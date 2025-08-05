package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store.get(id)가 null 일수도 있으니 Optional.ofNullable()로 감싸기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // 람다를 사용해 루프를 돌면서
                .filter(member -> member.getName().equals(name)) //member중 name을 가진것이 있으면
                .findAny(); //바로 보여주기?
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
