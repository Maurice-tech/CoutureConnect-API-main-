package com.fashion.Blog.model;

import com.fashion.Blog.enums.DesignType;
import com.fashion.Blog.enums.DesignTypeGender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(schema = "public")
@EntityListeners(AuditingEntityListener.class)

public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String postTitle;
    private String postDescription;

    @Enumerated(EnumType.STRING)
    private DesignType designType;

    @Enumerated(EnumType.STRING)
    private DesignTypeGender designTypeGender;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;
}
