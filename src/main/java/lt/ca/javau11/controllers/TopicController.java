package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.DTOs.TopicDto;
import lt.ca.javau11.entities.DTOs.TopicRequest;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    TopicService topicService;

    @GetMapping("/all")
    public List<TopicDto> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public TopicDto getTopic(@PathVariable Long id){
        return topicService.getTopic(id);
    }
    @PostMapping("/create")
    public Topic createTopic(@RequestBody TopicRequest topic) {
        return topicService.createTopic(topic);
    }
}
