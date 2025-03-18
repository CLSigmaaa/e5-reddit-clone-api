package edu.clone.reddit.services;

import edu.clone.reddit.dao.PostDao;
import edu.clone.reddit.dto.RequestPostDTO;
import edu.clone.reddit.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostDao postRepository;

    private String uploadDir = "uploads";

    public Post createPost(RequestPostDTO post, MultipartFile image) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Post newPost = new Post();

        if (image != null && !image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String fileDownloadUri = "/api/images/" + fileName;
            newPost.setImageUrl(fileDownloadUri);
        }

        newPost.setTitle(post.getTitle());
        newPost.setDescription(post.getDescription());
        newPost.setVoteCount(0);
        newPost.setCreatedBy("anonymous");
        newPost.setModified(false);
        return postRepository.save(newPost);

    }

    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = getPostById(id);
        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        post.setVoteCount(postDetails.getVoteCount());
        post.setComments(postDetails.getComments());
        post.setCreatedBy(postDetails.getCreatedBy());
        post.setModified(true);
        return postRepository.save(post);
    }

    // upvote post
    public Post upvotePost(Long id) {
        Post post = getPostById(id);
        post.setVoteCount(post.getVoteCount() + 1);
        return postRepository.save(post);
    }

    // downvote post
    public Post downvotePost(Long id) {
        Post post = getPostById(id);
        post.setVoteCount(post.getVoteCount() - 1);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}