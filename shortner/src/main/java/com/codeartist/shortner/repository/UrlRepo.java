package com.codeartist.shortner.repository;

import com.codeartist.shortner.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity,Long> {
    UrlEntity findByShortUrl(String shortUrl);
}
