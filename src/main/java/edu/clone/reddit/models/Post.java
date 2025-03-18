package edu.clone.reddit.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer voteCount;

    private String imageUrl;

    @ManyToOne
    private SubReddit subReddit;

    @OneToMany
    private List<Comment> comments;

    private String createdBy;

    private boolean isModified;

    //TODO: add tags
}
