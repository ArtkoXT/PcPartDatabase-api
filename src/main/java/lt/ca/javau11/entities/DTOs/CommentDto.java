package lt.ca.javau11.entities.DTOs;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String createTime;
    private String content;
    private Long topic_id;
    private Long author_id;
    private String author_name;
}
