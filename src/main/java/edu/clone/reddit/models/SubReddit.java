package edu.clone.reddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

@Entity
@Data
public class SubReddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnoreProperties("subReddit")
    @OneToMany
    private List<Subscriber> subscribers;

    @OneToMany
    private List<Post> posts;

    @CreationTimestamp
    private Long createdAt;

    private String createdBy;
}
