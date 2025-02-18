package lt.ca.javau11.entities.DTOs;

import lombok.Data;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.Topic;

@Data
public class TopicRequest {
    private Topic topic;
    private Comment comment;
    private Long author_id;
}
