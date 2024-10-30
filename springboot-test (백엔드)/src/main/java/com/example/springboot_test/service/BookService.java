package com.example.springboot_test.service;

import com.example.springboot_test.common.ResponseMessage;
import com.example.springboot_test.dto.request.PostBookRequestDto;
import com.example.springboot_test.dto.response.GetBookListResponseDto;
import com.example.springboot_test.dto.response.GetBookResponseDto;
import com.example.springboot_test.dto.response.PostBookResponseDto;
import com.example.springboot_test.dto.response.ResponseDto;
import com.example.springboot_test.entity.Book;
import com.example.springboot_test.repository.BookRepository;
import com.example.springboot_test.repository.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 1. 책 생성(Post)
    public ResponseDto<PostBookResponseDto> createBook(PostBookRequestDto requestDto) {
        Book book = new Book(
                null, requestDto.getBook_title(), requestDto.getBook_author(),
                requestDto.getBook_category()
        );

        Book savedBook = bookRepository.save(book);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, converToPostResponseDto(savedBook));
    }

    // 2. 전체 책 조회
    public List<GetBookListResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToListResponseDto)
                .collect(Collectors.toList());
    }

    // 3. 특정 ID 책 조회
    public GetBookResponseDto getBookById(Long id) {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));
            return convertToGetResponseDto(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new GetBookResponseDto();
        }
    }

    // 4, 특정 ID 책 수정
    public PostBookResponseDto updateBook(Long id, PostBookRequestDto updateDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));

        book.setBook_title(updateDto.getBook_title());
        book.setBook_author(updateDto.getBook_author());
        book.setBook_category(updateDto.getBook_category());

        Book updatedBook = bookRepository.save(book);
        return converToPostResponseDto(updatedBook);
    }

    // 5. 책 삭제
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Entity -> Response Dto 변환
    private GetBookResponseDto convertToGetResponseDto(Book book) {
        return new GetBookResponseDto(
                book.getId(), book.getBook_title(), book.getBook_author()
                , book.getBook_category()
        );
    }

    private PostBookResponseDto converToPostResponseDto(Book book) {
        return new PostBookResponseDto(
                book.getId(), book.getBook_title(), book.getBook_author()
        );
    }

    private GetBookListResponseDto convertToListResponseDto(Book book) {
        return new GetBookListResponseDto(
                book.getId(), book.getBook_title(), book.getBook_author()
        );
    }
}
