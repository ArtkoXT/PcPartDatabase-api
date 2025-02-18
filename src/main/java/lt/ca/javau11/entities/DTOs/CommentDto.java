package lt.ca.javau11.entities.DTOs;

import lombok.Data;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.entities.User;

import java.util.Date;

@Data
public class CommentDto {

    private Long id;
    private Date createTime;
    private String content;
    private Long topic_id;
    private Long author_id;
    private String author_name;
}
