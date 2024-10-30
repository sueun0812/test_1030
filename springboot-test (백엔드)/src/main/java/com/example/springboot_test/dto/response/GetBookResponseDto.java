package com.example.springboot_test.dto.response;

import com.example.springboot_test.repository.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookResponseDto {
    private Long id;
    private String book_title;
    private String book_author;
    private Category book_category;
}
