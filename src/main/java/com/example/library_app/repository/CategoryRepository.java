package com.example.library_app.repository;

import com.example.library_app.model.Book;
import com.example.library_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<
        Category,Long>, JpaSpecificationExecutor<Book> {
    Optional<Category> findByName(String name);
}
