package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class MessageController {

    CommentService commentService;

    @GetMapping("/topic/{topicId}/messages")
    public List<Comment> getMessages(@PathVariable Long topicId) {
        return commentService.getMessagesForTopic(topicId);
    }
}
