package com.example.library_app.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Entity
@Data
@Table(name = "users")
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity{
    @Column(unique = true)
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
