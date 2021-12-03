package com.example.test.s3;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.test.domain.Article;
import com.example.test.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Upload {

    private final AmazonS3Client amazonS3Client;
    private final Article article;
    private final ArticleService articleService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket = "spring-test-hk";

    public String upload(MultipartFile multipartFile, String dirName, Article article) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        return upload(uploadFile, dirName, article);
    }

    private String upload(File uploadFile, String dirName, Article article) {
        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        articleService.updateArticleImg(uploadImageUrl, article);

        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File converFile = new File(System.getProperty("User.dir") + "/" + file.getOriginalFilename());
        if (converFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(converFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(converFile);
        }

        return Optional.empty();
    }
}
