package com.example.library_app.service;

import com.example.library_app.dto.BookResponse;
import com.example.library_app.repository.BookRepository;
import com.example.library_app.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRecommendationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final BookListService bookListService;

    public BookRecommendationService(UserService userService, UserRepository userRepository, BookListService bookListService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.bookListService = bookListService;
    }


    @Cacheable("recommendation")
    public List<BookResponse> recommendBook()
    {
        return bookListService.listBooks(10,0,getRandomUserId());
    }

    private Long getRandomUserId() {
        return userRepository.findAll().stream().findAny().get().getId();
    }
}
