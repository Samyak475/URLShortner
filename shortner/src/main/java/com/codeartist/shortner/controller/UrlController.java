package com.codeartist.shortner.controller;

import com.codeartist.shortner.dtos.UrlRequest;
import com.codeartist.shortner.dtos.UrlResponseDto;
import com.codeartist.shortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponseDto> createUrl(@RequestBody UrlRequest urlRequest){
        try {
            UrlResponseDto urlResponseDto = urlService.createShort(urlRequest.getUrl());
            return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shorten/{shortUrl}")
    public ResponseEntity<UrlResponseDto>  getOriginalUrl(@PathVariable(name = "shortUrl") String shortUrl){
        try {
            UrlResponseDto urlResponseDto = urlService.getOriginal(shortUrl);
            return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/shorten/{shortUrl}")
    public ResponseEntity<UrlResponseDto>  updatelUrl(@PathVariable(name = "shortUrl") String shortUrl,@RequestBody UrlRequest urlRequest){
        try {
            UrlResponseDto urlResponseDto = urlService.updateUrl(urlRequest.getUrl(), shortUrl);
            return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/shorten/{shortUrl}")
    public ResponseEntity deleteUrl(@PathVariable(name = "shortUrl") String shortUrl){
        try {
            urlService.deleteUrl(shortUrl);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/shorten/{shortUrl}/{stats}")
    public ResponseEntity<UrlResponseDto>  getOriginalUrl(@PathVariable(name = "shortUrl") String shortUrl,
                                                          @PathVariable(name = "stats")String count){
        try {
            UrlResponseDto urlResponseDto = urlService.getStats(shortUrl);
            return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{shorUrl}")
    public ResponseEntity<Void> routeToOriganal(@PathVariable(name = "shortUrl") String shortUrl){
        try{
          URI redirectUrl =URI.create(urlService.getOriginal(shortUrl).getUrl());
          return ResponseEntity.status(HttpStatus.FOUND).location(redirectUrl).build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
