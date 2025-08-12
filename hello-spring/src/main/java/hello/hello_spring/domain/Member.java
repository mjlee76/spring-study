package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 생성해줌: identity전략
    private Long id;

    //@Column(name = "name") // 오른쪽에 db컬럼명 넣어줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
