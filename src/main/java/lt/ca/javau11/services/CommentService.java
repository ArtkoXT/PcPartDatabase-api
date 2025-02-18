package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.DTOs.CommentDto;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.entities.User;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.CommentRepository;
import lt.ca.javau11.repositories.TopicRepository;
import lt.ca.javau11.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    CommentRepository commentRepo;
    TopicRepository topicRepo;
    UserRepository userRepo;

    public List<CommentDto> getMessagesForTopic(Long topicId) {
        return commentRepo.findByTopicId(topicId).stream().map(this::toCommentDto).toList();
    }

    public CommentDto createComment(CommentDto commentDto) {

        Comment comment = new Comment();

        Topic topic = topicRepo.findById(commentDto.getTopic_id())
                .orElseThrow(() -> new NotFoundException("Topic not found!"));

        User author = userRepo.findById(commentDto.getAuthor_id())
                .orElseThrow( () -> new NotFoundException("User not found!"));

        comment.setContent(commentDto.getContent());
        comment.setAuthor(author);
        comment.setTopic(topic);
        comment.setCreateTime(new Date());

        commentRepo.save(comment);

        return toCommentDto(comment);
    }

    private CommentDto toCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor_id(comment.getAuthor().getId());
        commentDto.setTopic_id(comment.getTopic().getId());
        commentDto.setCreateTime(formatDate(comment.getCreateTime()));
        commentDto.setAuthor_name(comment.getAuthor().getUsername());

        return commentDto;
    }

    private String formatDate(Date date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .format(formatter);
    }
}
