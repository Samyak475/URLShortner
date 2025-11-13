package com.codeartist.shortner.service;

import com.codeartist.shortner.dtos.UrlResponseDto;
import com.codeartist.shortner.entity.UrlEntity;
import com.codeartist.shortner.repository.UrlRepo;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;

@Service
public class UrlService {

    @Autowired
    UrlRepo urlRepo;

    public UrlResponseDto createShort(String url){
        String shortUrl =Hashing.murmur3_32_fixed().hashString(url, StandardCharsets.UTF_8).toString();
        shortUrl =Base64.getEncoder().withoutPadding().encodeToString(shortUrl.getBytes(StandardCharsets.UTF_8));

      UrlEntity urlEntity=  UrlEntity.builder()
              .url(url)
              .shortUrl(shortUrl)
              .createdAt(LocalDate.now())
              .updatedAt(LocalDate.now())
              .count(1L)
              .build();
        UrlEntity urlEntity1 = urlRepo.findByUrl(url);
        if(urlEntity1!=null){
            throw new RuntimeException();
        }
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
        urlEntity.setCount(urlEntity.getCount()+1);
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
