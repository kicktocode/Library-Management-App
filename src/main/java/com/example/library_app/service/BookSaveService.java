package com.example.library_app.service;

import com.example.library_app.dto.BookDtoConverter;
import com.example.library_app.dto.BookListItemResponse;
import com.example.library_app.dto.request.SaveBookRequest;
import com.example.library_app.model.Book;
import com.example.library_app.model.Category;
import com.example.library_app.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookSaveService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CacheClient cacheClient;

    @Transactional
    @CacheEvict(key = "'saveBook_'+ #request.userId", value="bookList")
    public BookListItemResponse saveBook(SaveBookRequest request)
    {
        Category category= categoryService.loadCategory(request.getCategoryId());
        final Long userID = userService.findUserInContext().getId();
        final Book book= BookDtoConverter.convertToBookDto(request,category,userID);
        final Book fromDb = bookRepository.save(book);
        evictCache(request);
        return  BookDtoConverter.toItem(fromDb);
    }

    @Async
    private void evictCache(SaveBookRequest request)
    { final String statusCache= "status"+ request.getBookStatus()+request.getUserid();
        final String saveBookCache= "saveBook_"+request.getUserid();
        cacheClient.deleteAll(List.of(statusCache,saveBookCache));

    }

    public void deleteBook(Long bookId)
    {
        bookRepository.deleteById(bookId);
    }






}
