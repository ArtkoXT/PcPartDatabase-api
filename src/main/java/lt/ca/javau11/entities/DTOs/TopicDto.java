package lt.ca.javau11.entities.DTOs;

import lombok.Data;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.Topic;

@Data
public class TopicDto {

    private Long id;
    private String title;
    private Long author_id;
    private String author_name;
    private int messageCount;
}
