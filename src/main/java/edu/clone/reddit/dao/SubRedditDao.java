package edu.clone.reddit.dao;

import edu.clone.reddit.models.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubRedditDao extends JpaRepository<SubReddit, Long> {
    Optional<SubReddit> findByName(String name);

}
