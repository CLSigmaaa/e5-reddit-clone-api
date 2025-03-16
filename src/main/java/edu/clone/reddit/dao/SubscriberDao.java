package edu.clone.reddit.dao;

import edu.clone.reddit.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberDao extends JpaRepository<Subscriber, Long> {
}
