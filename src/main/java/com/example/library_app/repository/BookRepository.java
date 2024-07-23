package com.example.library_app.repository;

import com.example.library_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long>
        , JpaSpecificationExecutor<Book> {
    Optional<Book> filterByTitle(String title);
}
