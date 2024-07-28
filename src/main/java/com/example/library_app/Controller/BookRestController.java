package com.example.library_app.Controller;

import com.example.library_app.dto.BookListItemResponse;
import com.example.library_app.dto.BookResponse;
import com.example.library_app.dto.CategoryType;
import com.example.library_app.dto.request.SaveBookRequest;
import com.example.library_app.model.BookStatus;
import com.example.library_app.service.BookListService;
import com.example.library_app.service.BookSaveService;
import com.example.library_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookRestController {

    private  final BookListService bookListService;
    private  final BookSaveService bookSaveService;
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<BookListItemResponse> saveBook(@Valid @RequestBody SaveBookRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookSaveService.saveBook(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> listBook(@RequestParam
                                                                   (name = "size") int size,@RequestParam(name = "page")int page)
    {
        final long userId= userService.findUserInContext().getId();
        return ResponseEntity.ok(bookListService.listBooks(size,page,userId));
    }

    @GetMapping("/search/{categoryType}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable CategoryType categoryType)
    {
        final Long userId= userService.findUserInContext().getId();
        return ResponseEntity.ok(bookListService.searchByCategory(categoryType,userId));
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable BookStatus bookStatus)
    {
        final long userId= userService.findUserInContext().getId();
        return ResponseEntity.ok(bookListService.searchBookStatus(bookStatus,userId));
    }

    @GetMapping("/list/{title}")
    public ResponseEntity<List<BookResponse>> listByTitle(@PathVariable String title)
    {
        return ResponseEntity.ok( bookListService.searchByTitle(title));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId)
    {
        bookSaveService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<BookResponse> searchBook(@PathVariable Long bookId)
    {
        return ResponseEntity.ok(bookListService.findBook(bookId));
    }

}
