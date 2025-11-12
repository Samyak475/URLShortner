package com.codeartist.shortner.service;

import com.codeartist.shortner.dtos.UrlResponseDto;
import com.codeartist.shortner.entity.UrlEntity;
import com.codeartist.shortner.repository.UrlRepo;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class UrlService {

    @Autowired
    UrlRepo urlRepo;

    public UrlResponseDto createShort(String url){
        String shortUrl = Hashing.sha1().hashString(url, StandardCharsets.UTF_8).toString();
      UrlEntity urlEntity=  UrlEntity.builder()
              .url(url)
              .shortUrl(shortUrl)
              .createdAt(LocalDate.now())
              .updatedAt(LocalDate.now())
              .count(1L)
              .build();
      return EntityToRequest(urlRepo.save(urlEntity));
    }
    public UrlResponseDto EntityToRequest(UrlEntity urlEntity){
        return UrlResponseDto.builder()
                .id(urlEntity.getId())
                .count(urlEntity.getCount())
                .shortUrl(urlEntity.getShortUrl())
                .url(urlEntity.getUrl())
                .updatedAt(urlEntity.getUpdatedAt())
                .createdAt(urlEntity.getCreatedAt())
                .build();
    }

    public UrlResponseDto updateUrl(String url, String  shortUrl){
        UrlEntity urlEntity = urlRepo.findByShortUrl(shortUrl);
        if(urlEntity==null)
        {
            throw new RuntimeException();
        }
        urlEntity.setUrl(url);
        return EntityToRequest(urlRepo.save(urlEntity));
    }
    public void deleteUrl(String shortUrl){
        UrlEntity urlEntity =  urlRepo.findByShortUrl(shortUrl);
        if(urlEntity==null){
            throw new RuntimeException();
        }
        urlRepo.deleteById(urlEntity.getId());

    }
    public UrlResponseDto getStats(String shortUrl){
        UrlEntity urlEntity = urlRepo.findByShortUrl(shortUrl);
        if(urlEntity==null)
        {
            throw new RuntimeException();
        }
      return EntityToRequest(urlEntity);
    }

    public UrlResponseDto getOriginal(String shortUrl){
        UrlEntity urlEntity = urlRepo.findByShortUrl(shortUrl);
        if(urlEntity==null)
        {
            throw new RuntimeException();
        }
        return EntityToRequest(urlEntity);
    }
}
