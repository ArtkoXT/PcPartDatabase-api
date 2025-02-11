package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    MessageRepository messageRepo;

    public List<Comment> getMessagesForTopic(Long topicId) {
        return messageRepo.findByTopicId(topicId);
    }
}
