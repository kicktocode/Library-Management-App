package com.example.library_app.dto.request;

import com.example.library_app.model.BookStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookRequest {
    private Long userid;
    @NotBlank
    private String title;
    @NotBlank
    private String authorName;
    @NotBlank
    private BookStatus bookStatus;
    @NotBlank
    private String publisher;
    @NotNull
    private Integer lastPageNumber;
    @NotNull
    private Long categoryId;
    @NotNull
    private Integer totalPage;
}
