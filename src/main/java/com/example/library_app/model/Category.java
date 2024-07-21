package com.example.library_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
@Builder(toBuilder = true)
@Log4j2
@Getter
@Setter
public class Category extends BaseEntity {
    private String name;
    @JsonIgnore
    private List<Book> books;
}
