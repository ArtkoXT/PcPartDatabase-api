package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {

    TopicRepository topicRepo;

    public Topic addTopic(Topic topic){
        return topicRepo.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepo.findAll();
    }
}
