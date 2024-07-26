package com.example.library_app.dto.request;

import com.example.library_app.model.BookStatus;
import lombok.Data;

@Data
public class BookUpdateRequest {
    private Long id;
    private String title;
    private String authorName;
    private BookStatus bookStatus;
    private String publisher;
    private Integer lastPageNumber;
    private Long categoryId;
    private Integer totalPage;

}
