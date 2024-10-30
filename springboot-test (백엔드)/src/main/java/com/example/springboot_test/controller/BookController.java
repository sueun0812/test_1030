package com.example.springboot_test.controller;

import com.example.springboot_test.common.ApiMappingPattern;
import com.example.springboot_test.dto.request.PostBookRequestDto;
import com.example.springboot_test.dto.response.GetBookListResponseDto;
import com.example.springboot_test.dto.response.GetBookResponseDto;
import com.example.springboot_test.dto.response.PostBookResponseDto;
import com.example.springboot_test.dto.response.ResponseDto;
import com.example.springboot_test.repository.Category;
import com.example.springboot_test.service.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOOK)
@RequiredArgsConstructor
// 초기화 되지 않은 final 필드나 @NonNull이 붙은 필드에 대해 생성자를 생성
public class BookController {
    // Service 객체를 주입 받아 사용하는 변수
    private final BookService bookService;

    // 생성자 주입 - RequiredArgsConstructor로 대체
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    // 책 생성
    @PostMapping
    public ResponseEntity<ResponseDto<PostBookResponseDto>> createBook(@RequestBody PostBookRequestDto requestDto) {
        ResponseDto<PostBookResponseDto> result = bookService.createBook(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 전체 책 조회
    @GetMapping
    public ResponseEntity<List<GetBookListResponseDto>> getAllBooks() {
        List<GetBookListResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // 단건 책 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetBookResponseDto> getBookById(@PathVariable Long id) {
        GetBookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // 책 수정
    @PutMapping("/{id}")
    public ResponseEntity<PostBookResponseDto> updateBook(
            @PathVariable Long id, @RequestBody PostBookRequestDto requestDto) {
        PostBookResponseDto updatedBookResponse = bookService.updateBook(id, requestDto);
        return ResponseEntity.ok(updatedBookResponse);
    }

    // 책 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
