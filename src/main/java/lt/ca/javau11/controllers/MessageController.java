package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Message;
import lt.ca.javau11.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class MessageController {

    MessageService messageService;

    @GetMapping("/topic/{topicId}/messages")
    public List<Message> getMessages(@PathVariable Long topicId) {
        return messageService.getMessagesForTopic(topicId);
    }
}
