package com.example.springboot_test.dto.request;

import com.example.springboot_test.repository.Category;
import lombok.*;


// 책 생성 시 클라이언트가 서버에 전달하는 데이터
// : 요청에 대한 데이터
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBookRequestDto {
    private String book_title;
    private String book_author;
    private Category book_category;
}