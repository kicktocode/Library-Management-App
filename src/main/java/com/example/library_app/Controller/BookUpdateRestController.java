package com.example.library_app.Controller;

import com.example.library_app.dto.BookListItemResponse;
import com.example.library_app.dto.request.BookUpdateRequest;
import com.example.library_app.service.BookUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookUpdate")
@RequiredArgsConstructor

public class BookUpdateRestController {
    private final BookUpdateService bookUpdateService;

    @PutMapping("/update")
    public ResponseEntity<BookListItemResponse> updateBook(@RequestBody BookUpdateRequest request )
    {
        return ResponseEntity.ok(bookUpdateService.updateBook(request));
    }
}
