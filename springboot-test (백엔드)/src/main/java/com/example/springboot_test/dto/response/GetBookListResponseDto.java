package com.example.springboot_test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookListResponseDto {
    private Long id;
    private String book_title;
    private String book_author;
}
