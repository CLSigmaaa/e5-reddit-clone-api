// PostController.java
package edu.clone.reddit.controllers;

import edu.clone.reddit.dto.RequestPostDTO;
import edu.clone.reddit.models.Post;
import edu.clone.reddit.services.PostService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Post> createPost(
            @RequestPart("post") RequestPostDTO post,
            @Nullable @RequestPart("image") MultipartFile image) throws IOException {
            Post createdPost = postService.createPost(post, image);
            return ResponseEntity.ok(createdPost);
    }

    // upvote post
    @PostMapping("/{id}/upvote")
    public ResponseEntity<Post> upvotePost(@PathVariable Long id) {
        Post upvotedPost = postService.upvotePost(id);
        return ResponseEntity.ok(upvotedPost);
    }

    // downvote post
    @PostMapping("/{id}/downvote")
    public ResponseEntity<Post> downvotePost(@PathVariable Long id) {
        Post downvotedPost = postService.downvotePost(id);
        return ResponseEntity.ok(downvotedPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post updatedPost = postService.updatePost(id, postDetails);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}