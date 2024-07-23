package com.example.library_app.dto;

import com.example.library_app.model.Book;
import com.example.library_app.model.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BookListItemResponse {
    private String title;
    private String authorName;
    private Long userId;
    private String publisher;
    private String categoryName;
    private Long id;
    private BookStatus bookStatus;
    private Integer lastPageNumber;
    private Integer totalPage;

    public static BookListItemResponse from(Book book)
    {
         return BookListItemResponse.builder()
                 .authorName(book.getAuthorName())
                 .bookStatus(book.getBookStatus())
                 .id(book.getId())
                 .categoryName(book.getCategory().getName())
                 .lastPageNumber(book.getLastPageNumber())
                 .title(book.getTitle())
                 .publisher(book.getPublisher())
                 .userId(book.getUserId())
                 .totalPage(book.getTotalPage())
                 .build();

    }

}
