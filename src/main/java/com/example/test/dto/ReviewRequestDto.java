package com.example.test.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewRequestDto {
    private String content;

    public ReviewRequestDto(String content) {
        this.content = content;
    }
}
