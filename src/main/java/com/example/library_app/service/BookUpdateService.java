package com.example.library_app.service;

import com.example.library_app.dto.BookListItemResponse;
import com.example.library_app.dto.ErrorCode;
import com.example.library_app.dto.request.BookUpdateRequest;
import com.example.library_app.exceptions.GenericException;
import com.example.library_app.model.Book;
import com.example.library_app.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookUpdateService {
    private final BookRepository bookRepository;

    public BookListItemResponse updateBook(BookUpdateRequest updateRequest) {
        final long id = updateRequest.getId();
        final Book book = bookRepository.findById(id).orElseThrow(() -> GenericException.builder().errorCode(ErrorCode.BOOK_NOT_FOUND).build());

        updateAttributes(updateRequest, book);
        bookRepository.save(book);
        return BookListItemResponse.from(book);
    }
    private static void updateAttributes(BookUpdateRequest updateRequest,Book book)
    {
        book.setAuthorName(getOrDefault(updateRequest.getAuthorName(), book.getAuthorName()));
        book.setBookStatus(getOrDefault(updateRequest.getBookStatus(),book.getBookStatus()));
        book.setTitle(getOrDefault(updateRequest.getTitle(), book.getTitle()));
        book.setPublisher(getOrDefault(updateRequest.getPublisher(), book.getPublisher()));
        book.setTotalPage(getOrDefault(updateRequest.getTotalPage(), book.getTotalPage()));
        book.setLastPageNumber(getOrDefault(updateRequest.getLastPageNumber(), book.getLastPageNumber()));
    }



    private static <T> T getOrDefault(T data, T defaultValue)
    {
        return data == null?defaultValue:data;
    }
}
