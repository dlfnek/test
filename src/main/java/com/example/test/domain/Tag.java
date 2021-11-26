package com.example.test.domain;


import com.example.test.dto.TagRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(nullable = false)
    private String tagName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "article_tag", nullable = false)
    private Article article;

    public Tag(TagRequestDto requestDto, Article article) {
        this.tagName = requestDto.getTagName();
        this.article = article;
    }
}
