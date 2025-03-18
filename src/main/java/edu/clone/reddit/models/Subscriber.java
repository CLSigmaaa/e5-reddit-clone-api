package edu.clone.reddit.models;

import edu.clone.reddit.enums.SubscriberRoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    private SubReddit subreddit;

    private SubscriberRoleEnum role;
}
