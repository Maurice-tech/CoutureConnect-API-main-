package com.fashion.Blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass

public abstract class BaseEntity {
    private Timestamp createdAt;
}
