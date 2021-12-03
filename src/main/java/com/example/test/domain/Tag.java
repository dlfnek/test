package com.example.test.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.REMOVE}) // 있는 거 없는 거 test 해보기!!! 왜 Comment에는 없는가!
    // fetch = 로딩 타입을 선택하는 옵션, EAGER -> 즉시 로딩, LAZY -> 지연 로딩. // CASCADE -> 종속성에 관련된 옵션,  Persist=엮여있는 정보(외래키)를 같이 저장한다., Remove=엮여 있는 애가 지워지면 나도 지워진다. (즉, 중복 체크를 통해 데이터 정합성에 기여함.)
    @JoinColumn(name = "article_idx", nullable = false) // 여기서 name은 큰 의미가 없는 것 같은데 맞나 ?
    private Article article;

    public Tag(String name, Article article) {
        this.name = name;
        this.article = article;
    }
}

//    public Tag(TagRequestDto requestDto, Article article) {
//        this.tagName = requestDto.getTagName(); // Tag 값이 뭉탱이로 저장이 된다. tag를 하나하나씩 raw로 만들어주기 위함
//        this.article = article;
//    }
