package edu.clone.reddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class SubReddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String iconUrl;

    @JsonIgnoreProperties("subReddit")
    @OneToMany
    private List<Subscriber> subscribers;

    @OneToMany
    private List<Post> posts;

//    @CreationTimestamp
//    private LocalDateTime createdAt;

    private String createdBy;
}
