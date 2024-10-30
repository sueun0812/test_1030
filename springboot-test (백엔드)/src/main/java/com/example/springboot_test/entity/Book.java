package com.example.springboot_test.entity;

import com.example.springboot_test.repository.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String book_title;

    @Column(nullable = false, length = 100)
    private String book_author;

    @Enumerated(EnumType.STRING)
    // JPA에서 열거형 데이터를 DB에 저장할 때 방식을 지정
    // : enum의 이름을 문자열로 저장
    @Column(nullable = false)
    private Category book_category;


}