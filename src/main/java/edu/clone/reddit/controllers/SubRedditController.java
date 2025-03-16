package edu.clone.reddit.controllers;

import edu.clone.reddit.enums.SubscriberRoleEnum;
import edu.clone.reddit.models.Post;
import edu.clone.reddit.models.SubReddit;
import edu.clone.reddit.models.Subscriber;
import edu.clone.reddit.services.SubRedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
public class SubRedditController {
    @Autowired
    private SubRedditService subRedditService;

    @PostMapping
    public ResponseEntity<SubReddit> createSubReddit(@RequestBody SubReddit subReddit) {
        SubReddit createdSubReddit = subRedditService.createSubReddit(subReddit);
        return ResponseEntity.ok(createdSubReddit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubReddit> getSubRedditById(@PathVariable Long id) {
        SubReddit subReddit = subRedditService.getSubRedditById(id);
        return ResponseEntity.ok(subReddit);
    }

    @GetMapping
    public ResponseEntity<List<SubReddit>> getAllSubReddits() {
        List<SubReddit> subReddits = subRedditService.getAllSubReddits();
        return ResponseEntity.ok(subReddits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubReddit> updateSubReddit(@PathVariable Long id, @RequestBody SubReddit subRedditDetails) {
        SubReddit updatedSubReddit = subRedditService.updateSubReddit(id, subRedditDetails);
        return ResponseEntity.ok(updatedSubReddit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubReddit(@PathVariable Long id) {
        subRedditService.deleteSubReddit(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<SubReddit> addPostToSubReddit(@PathVariable Long id, @RequestBody Post post) {
        SubReddit updatedSubReddit = subRedditService.addPostToSubReddit(id, post);
        return ResponseEntity.ok(updatedSubReddit);
    }

    @PostMapping("/{id}/subscribers/user")
    public ResponseEntity<SubReddit> addSubscriberToSubReddit(@PathVariable Long id, @RequestBody Subscriber subscriber) {
        SubReddit updatedSubReddit = subRedditService.addSubscriberToSubReddit(id, subscriber);
        return ResponseEntity.ok(updatedSubReddit);
    }

    @PostMapping("/{id}/subscribers/moderator")
    public ResponseEntity<SubReddit> addModeratorToSubReddit(@PathVariable Long id, @RequestBody Subscriber subscriber) {
        SubReddit updatedSubReddit = subRedditService.addModeratorToSubReddit(id, subscriber);
        return ResponseEntity.ok(updatedSubReddit);
    }
}
