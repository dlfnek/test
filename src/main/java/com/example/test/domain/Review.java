package com.example.test.domain;


import com.example.test.dto.ReviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    public Review(String content) {
        this.content = content;
    }

    public void update(ReviewRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public Review(ReviewRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

}
