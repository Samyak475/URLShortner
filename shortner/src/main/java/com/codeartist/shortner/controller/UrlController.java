package com.codeartist.shortner.controller;

import com.codeartist.shortner.dtos.UrlRequest;
import com.codeartist.shortner.dtos.UrlResponseDto;
import com.codeartist.shortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/shorten/")
    public ResponseEntity<UrlResponseDto> createUrl(@RequestBody UrlRequest urlRequest){
      UrlResponseDto urlResponseDto=  urlService.createShort(urlRequest.getUrl());
      return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/shortUrl")
    public ResponseEntity<UrlResponseDto>  getOriginalUrl(@RequestParam(name = "shortUrl") String shortUrl){
        UrlResponseDto urlResponseDto=  urlService.getOriginal(shortUrl);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/shortUrl")
    public ResponseEntity<UrlResponseDto>  updatelUrl(@RequestParam(name = "shortUrl") String shortUrl,@RequestBody UrlRequest urlRequest){
        UrlResponseDto urlResponseDto=  urlService.updateUrl(urlRequest.getUrl(), shortUrl);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/shortUrl")
    public ResponseEntity deleteUrl(@RequestParam(name = "shortUrl") String shortUrl){
          urlService.deleteUrl(shortUrl);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }
    @GetMapping("/shortUrl/stats")
    public ResponseEntity<UrlResponseDto>  getOriginalUrl(@RequestParam(name = "shortUrl") String shortUrl,
                                                          @RequestParam(name = "stats")String count){
        UrlResponseDto urlResponseDto=  urlService.getStats(shortUrl);
        return new ResponseEntity<>(urlResponseDto, HttpStatus.ACCEPTED);
    }

}
