package com.example.test.domain;


import com.example.test.dto.ArticleCommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(nullable = false)
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "article_idx", nullable = false)
    private Article article;

    public Comment(ArticleCommentRequestDto requestDto, Article article) {
        this.comment = requestDto.getComment();
        this.article = article;
    }
}
