package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    // Test코드를 작성해보자
    // Test코드는 다른데서 사용안하니 class 앞에 public은 빼자
    // Test는 junit을 사용해본다.
    // @Test 어노테이션을 이용하면 메서드를 실행해볼 수 있다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 클래스 전체를 돌리면 테스트 메서드가 오류나는 문제 발생
    // 객체가 겹쳐서 그럼
    // 메서드가 끝날때마다 store변수를 초기화 하는 메서드 작성
    @AfterEach // 메서드 한개가 끝날때 마다 자동 동작하는 어노테이션
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // 검증
        Member result = repository.findById(member.getId()).get();
        // repository.findById(member.getId())의 반환값이 Optional이기 때문에
        // .get()으로 값을 꺼내주고 그걸 result로 저장
        // 옵 + 커맨드 + V -> 반환값 확인 가능

        //System.out.println("result = " + (result == member)); //같은 값인지를 true,false로만 확인 가능
        //Assertions.assertEquals(member, result); //junit의 Assertions를 이용해 비교가능
        Assertions.assertThat(member).isEqualTo(result); //assertj를 이용해 비교가능
        //Assertions를 옵+엔터 로 static으로 변경하면 assertThat부터 작성 가능 -> 코드 간결
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //검증
        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
