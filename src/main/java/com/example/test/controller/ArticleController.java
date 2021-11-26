package com.example.test.controller;


import com.example.test.domain.Article;
import com.example.test.dto.ArticleCommentRequestDto;
import com.example.test.dto.ArticleRequestDto;
import com.example.test.dto.TagRequestDto;
import com.example.test.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Article setArticle(@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.setArticle(articleRequestDto);
    }

    @GetMapping("/articles")
    public List<Article> getArticles(){
        return articleService.getArticles();
    }

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id){
        return articleService.getArticle(id);
    }


    @PostMapping("/article/comment")
    public void  setArticleComment(@RequestBody ArticleCommentRequestDto articleCommentRequestDto){
        articleService.setArticleComment(articleCommentRequestDto);
    }

    @PostMapping("/article/tag")
    public void tagsInput(@RequestBody TagRequestDto tagRequestDto) {
        articleService.setTag(tagRequestDto);
    }
}
