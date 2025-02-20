package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.DTOs.CommentDto;
import lt.ca.javau11.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {

    CommentService commentService;

    @GetMapping("/api/topic/{topicId}/comments")
    public List<CommentDto> getMessages(@PathVariable Long topicId) {
        return commentService.getMessagesForTopic(topicId);
    }

    @PostMapping("/api/comments/add")
    public CommentDto createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @PutMapping("/api/comments/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return commentService.updateComment(id, commentDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        boolean isDeleted = commentService.deleteComment(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
