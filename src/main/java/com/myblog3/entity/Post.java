package com.myblog3.entity;

//import jakarta.persistence.*;
import java.util.*;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Comment> comments;
}
