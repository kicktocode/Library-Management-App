package com.example.library_app.dto;

import com.example.library_app.model.Book;
import com.example.library_app.model.Category;

public class BookDtoConverter {
    public static Book convertToBookDto(SaveBookRequest request,
                                        Category category,Long userId)
    {
        return Book.builder()
                .category(category)
                .bookStatus(request.getBookStatus())
                .authorName(request.getAuthorName())
                .lastPageNumber(request.getLastPageNumber())
                .title(request.getTitle())
                .publisher(request.getPublisher())
                .totalPage(request.getTotalPage())
                .userId(userId)
                .build();
    }

    public static  BookListItemResponse toItem(Book fromDb)
    {
        return  BookListItemResponse.builder()
    }
}
