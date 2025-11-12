package com.codeartist.shortner.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UrlResponseDto {
    @JsonIgnore
    private Long id;
    private String url;
    @JsonIgnore
    private String shortUrl;
    @JsonIgnore
    private LocalDate createdAt;
    @JsonIgnore
    private LocalDate updatedAt;
    @JsonIgnore
    private Long count;
}
