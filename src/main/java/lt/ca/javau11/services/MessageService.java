package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Message;
import lt.ca.javau11.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    MessageRepository messageRepo;

    public List<Message> getMessagesForTopic(Long topicId) {
        return messageRepo.findByTopicId(topicId);
    }
}
