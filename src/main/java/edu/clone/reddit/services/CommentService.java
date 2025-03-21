package edu.clone.reddit.services;

import edu.clone.reddit.dao.CommentDao;
import edu.clone.reddit.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = getCommentById(id);
        comment.setContent(commentDetails.getContent());
        comment.setPost(commentDetails.getPost());
        comment.setParentComment(commentDetails.getParentComment());
        comment.setChildComments(commentDetails.getChildComments());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        comment.setDeleted(true);
        comment.setContent("[deleted]");
    }

    // upvote comment
    public Comment upvoteComment(Long id) {
        Comment comment = getCommentById(id);
        comment.setVoteCount(comment.getVoteCount() + 1);
        return commentRepository.save(comment);
    }
}