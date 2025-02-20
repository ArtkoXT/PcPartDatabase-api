package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.DTOs.TopicDto;
import lt.ca.javau11.entities.DTOs.TopicRequest;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.entities.User;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.CommentRepository;
import lt.ca.javau11.repositories.TopicRepository;
import lt.ca.javau11.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicService {

    TopicRepository topicRepo;
    CommentRepository commentRepo;
    UserRepository userRepo;

    public Topic addTopic(Topic topic){
        return topicRepo.save(topic);
    }

    public Topic createTopic(TopicRequest topicRequest) {
        User author = userRepo.findById(topicRequest.getAuthor_id()).orElseThrow(() -> new NotFoundException("User not found!"));

        Topic topic = topicRequest.getTopic();
        topic.setAuthor(author);

        Comment comment = topicRequest.getComment();
        comment.setAuthor(author);
        comment.setCreateTime(new Date());

        comment.setTopic(topicRepo.save(topic));

        commentRepo.save(comment);
        return topic;
    }

    public TopicDto getTopic(Long id) {
        Topic topic = topicRepo.findById(id).orElseThrow(() -> new NotFoundException("Topic not found!"));
        TopicDto topicDto = new TopicDto();
        topicDto.setAuthor_id(topic.getAuthor().getId());
        topicDto.setTitle(topic.getTitle());
        topicDto.setAuthor_name(topic.getAuthor().getUsername());
        topicDto.setMessageCount(topic.getMessageCount());
        return topicDto;
    }

    public List<TopicDto> getAllTopics() {
        return topicRepo.findAll().stream().map((this::toTopicDto)).toList();
    }

    public boolean deleteTopic(Long id) {
        Optional<Topic> topic = topicRepo.findById(id);

        if(topic.isEmpty())
            return false;

        topicRepo.delete(topic.get());
        return true;
    }

    private TopicDto toTopicDto(Topic topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setAuthor_id(topic.getAuthor().getId());
        topicDto.setTitle(topic.getTitle());
        topicDto.setAuthor_name(topic.getAuthor().getUsername());
        topicDto.setMessageCount(topic.getMessageCount());

        return topicDto;
    }
}
