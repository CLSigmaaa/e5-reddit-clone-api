package edu.clone.reddit.services;

import edu.clone.reddit.dao.SubRedditDao;
import edu.clone.reddit.enums.SubscriberRoleEnum;
import edu.clone.reddit.models.Post;
import edu.clone.reddit.models.SubReddit;
import edu.clone.reddit.models.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class SubRedditService {

    @Autowired
    private SubRedditDao subRedditRepository;

    public SubReddit createSubReddit(SubReddit subReddit) {
        return subRedditRepository.save(subReddit);
    }

    public SubReddit getSubRedditById(Long id) {
        Optional<SubReddit> subReddit = subRedditRepository.findById(id);
        return subReddit.orElseThrow(() -> new RuntimeException("SubReddit not found"));
    }

    public List<SubReddit> getAllSubReddits() {
        return subRedditRepository.findAll();
    }

    public SubReddit updateSubReddit(Long id, SubReddit subRedditDetails) {
        SubReddit subReddit = getSubRedditById(id);
        subReddit.setName(subRedditDetails.getName());
        subReddit.setCreatedBy(subRedditDetails.getCreatedBy());
        return subRedditRepository.save(subReddit);
    }

    public void deleteSubReddit(Long id) {
        subRedditRepository.deleteById(id);
    }

    public SubReddit addPostToSubReddit(Long id, Post post) {
        SubReddit subReddit = getSubRedditById(id);
        subReddit.getPosts().add(post);
        return subRedditRepository.save(subReddit);
    }

    public SubReddit addSubscriberToSubReddit(Long id, Subscriber subscriber) {
        SubReddit subReddit = getSubRedditById(id);
        subReddit.getSubscribers().add(subscriber);
        subscriber.setRole(SubscriberRoleEnum.USER);
        return subRedditRepository.save(subReddit);
    }

    // add moderator to subreddit
    public SubReddit addModeratorToSubReddit(Long id, Subscriber subscriber) {
        SubReddit subReddit = getSubRedditById(id);
        subReddit.getSubscribers().add(subscriber);
        subscriber.setRole(SubscriberRoleEnum.MODERATOR);
        return subRedditRepository.save(subReddit);
    }
}