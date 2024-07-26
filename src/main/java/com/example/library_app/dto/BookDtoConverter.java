package com.example.library_app.dto;

import com.example.library_app.dto.request.SaveBookRequest;
import com.example.library_app.model.Book;
import com.example.library_app.model.Category;
import lombok.experimental.SuperBuilder;


public class BookDtoConverter {
    public static Book convertToBookDto(SaveBookRequest request, Category category, Long userId) {
        return Book.builder()
                .bookStatus(request.getBookStatus())
                .title(request.getTitle())
                .category(category)
                .publisher(request.getPublisher())
                .userId(userId)
                .totalPage(request.getTotalPage())
                .lastPageNumber(request.getLastPageNumber())
                .authorName(request.getAuthorName())
                .build();
    }

    public static BookListItemResponse toItem(Book fromDb) {
        return BookListItemResponse.builder()
                .bookStatus(fromDb.getBookStatus())
                .lastPageNumber(fromDb.getLastPageNumber())
                .authorName(fromDb.getAuthorName())
                .lastPageNumber(fromDb.getLastPageNumber())
                .categoryName(fromDb.getAuthorName())
                .publisher(fromDb.getPublisher())
                .title(fromDb.getTitle())
                .userId(fromDb.getUserId())
                .totalPage(fromDb.getTotalPage())
                .build();
    }


}















