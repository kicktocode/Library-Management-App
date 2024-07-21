package com.example.library_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book")
@Builder(toBuilder = true)
@Log4j2
public class Book extends BaseEntity{

    private String title;
    private String authorName;
    private String publisher;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    private Integer lastPageNumber;
    private Integer totalPage;
    private Category category;
    private Long userId;


}
