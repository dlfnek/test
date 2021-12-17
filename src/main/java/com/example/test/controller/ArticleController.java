package com.example.test.controller;


import com.example.test.domain.Article;
import com.example.test.dto.ArticleCommentRequestDto;
import com.example.test.dto.ArticleRequestDto;
import com.example.test.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Article setArticle(ArticleRequestDto articleRequestDto) throws IOException {
        return articleService.setArticle(articleRequestDto);
    }

    @GetMapping("/articles")
    public List<Article> getArticles(@RequestParam(required = false) String searchTag){
        return articleService.getArticles(searchTag);
    }

    // @RequestParam = 쿼리스트링의 키 값에 맞는 값을 가져옴
        // -> RequestParam 을 사용한 이유를 추측해본 결과, PathVariable은 URI 경로상 위치한 것을 찾아내기 때문에 별도의 페이지로 들어가는 작업을 해야한다.
        // 그러나, 태그의 경우 현재 보여지는 경로에서 그대로 노출되어야 함과 동시에, 쿼리를 통한 검색을 구현하기 위해 RequestParam을 사용했다고 추측한다.

    // @PathVariable = URI 경로상 위치에 맞는 값을 가져옴
        // 즉, RequestParam과 PathVarialbe은 파라미터를 통해 값을 입력받기 위한 어노테이션이라는 점에서 비슷하지만,
        // 작동방식이 달라 구현 목적에 따라 다르게 사용될 뿐이다.

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id){
        return articleService.getArticle(id);
    }


    @PostMapping("/article/comment")
    public void  setArticleComment(@RequestBody ArticleCommentRequestDto articleCommentRequestDto){
        articleService.setArticleComment(articleCommentRequestDto);
    }
}
