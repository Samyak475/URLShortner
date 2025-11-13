package com.codeartist.shortner.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UrlResponseDto {
    @Nullable
    private Long id;
    private String url;
    @Nullable
    private String shortUrl;
    @Nullable
    private LocalDate createdAt;
    @Nullable
    private LocalDate updatedAt;
    @Nullable
    private Long count;
}
