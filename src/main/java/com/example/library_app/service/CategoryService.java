package com.example.library_app.service;

import com.example.library_app.dto.ErrorCode;
import com.example.library_app.exceptions.GenericException;
import com.example.library_app.model.Category;
import com.example.library_app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByName(String name)
    {
       return categoryRepository.findByName(name).orElseThrow(()->
                GenericException.builder().errorCode(ErrorCode.CATEGORY_NOT_FOUND).build());

    }

    public Category loadCategory(Long id)
    {
        return  categoryRepository.findById(id).orElseThrow(
                ()->GenericException.builder().errorCode(ErrorCode.CATEGORY_NOT_FOUND).build()
        );
    }
}
