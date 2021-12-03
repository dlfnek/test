package com.example.test.service;


import com.example.test.domain.Article;
import com.example.test.domain.Comment;
import com.example.test.domain.Tag;
import com.example.test.dto.ArticleCommentRequestDto;
import com.example.test.dto.ArticleRequestDto;
import com.example.test.repository.ArticleRepository;
import com.example.test.repository.CommentRepository;
import com.example.test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Article setArticle(ArticleRequestDto articleRequestDto){
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);

        List<String> items = Arrays.asList(articleRequestDto.getTags().split("\\s*,\\s*"));
        List<Tag> tags = items.stream().map(tag -> new Tag(tag, article)).collect(Collectors.toList());
        tagRepository.saveAll(tags);

        return article;

        // items의 역할은 무엇인가 ? Array.asList에 공백을 기준으로 구분하여 태그값을 입력받음.

        // Arrays.asList -> List를 구현하기 위한 객체 메소드 중 하나 (비교군: List.of)
        // Arrays.asList = mutable한 List 구현체(Collection) - 변경 가능, Null 허용
        // List.of = immutable한 Collection - 변경 불가능, Null 불가

        // regex -> 정규표현식(Regular expressions)의 줄임말 = 문자열에 어떤 패턴의 문자들이 있는지 찾는데 도움을 주는 식
        // \\s* 정규표현식의 패턴 중 하나로, whitespace 1개를 말함. (즉, \\s*,\\s*는 공백을 기준으로 값을 split 하겠다는 의미) ex) 태그1, 태그2 일 경우, 태그1만 검색해도 파라미터 값으로 입력되어 검색이 가능하게 됨.

            // 번외 Arrays.asList vs ArrayList의 차이, Arrays.asList(배열의 크기가 고정 [add, remove 등 크기가 변경되는 연산 지원 X]
            // ArrayList(배열의 크기를 변경 가능 [add, remove 등 연산 지원O], 그렇다면 여기에 Arrays.asList를 사용한 이유는 무엇인지 튜터님께 질문하기.

        // stream().map() 일단, stream의 경우 for, foreach문 대신 람다 함수형식으로 요소를 처리하기 위한 명령어.
        // map = 요소들을 특정 조건에 해당하는 값으로 변환하여 줌. 여기서는 article을 기준으로 입력된 tag를 List로 모아 새로운 List를 만들겠다는 의미
        // filter = 요소들을 조건에 따라 걸러내는 작업
        // sorted = 요소들을 정렬하는 작업
    }

    public Article getArticle(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Article> getArticles(String searchTag){ //searchTag 는 어디에 ??
        if(searchTag.isEmpty()) {
            return articleRepository.findAll();
        } else {
            return articleRepository.findAllByTagsName(searchTag);
        }
    }

    @Transactional
    public void setArticleComment(ArticleCommentRequestDto articleCommentRequestDto){
        Article article = articleRepository.findById(articleCommentRequestDto.getIdx()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        Comment comment = new Comment(articleCommentRequestDto, article);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateArticleImg(String uploadImageUrl, Article article) {
        Article article1 = articleRepository.findById(article.getIdx()).orElseThrow(
                () -> new NullPointerException("해당 글이 없습니다."));

    }
}