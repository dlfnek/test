package com.example.test.service;


import com.example.test.domain.Article;
import com.example.test.domain.Comment;
import com.example.test.domain.Tag;
import com.example.test.dto.ArticleCommentRequestDto;
import com.example.test.dto.ArticleRequestDto;
import com.example.test.dto.TagRequestDto;
import com.example.test.repository.ArticleRepository;
import com.example.test.repository.CommentRepository;
import com.example.test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    public Article setArticle(ArticleRequestDto articleRequestDto){
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);
        return article;
    }

    public Article getArticle(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Article> getArticles(){
        return articleRepository.findAll();
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
    public void setTag(TagRequestDto tagRequestDto) {
        Article article = articleRepository.findById(tagRequestDto.getIdx()).orElseThrow(
                () -> new IllegalArgumentException("해당 태그가 존재하지 않습니다.")
        );
        Tag tag = new Tag(tagRequestDto, article);
        tagRepository.save(tag);
    }
}