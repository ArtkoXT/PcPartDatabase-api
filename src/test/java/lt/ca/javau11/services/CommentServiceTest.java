package lt.ca.javau11.services;

import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.DTOs.CommentDto;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.entities.User;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.CommentRepository;
import lt.ca.javau11.repositories.TopicRepository;
import lt.ca.javau11.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepo;

    @Mock
    private TopicRepository topicRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private CommentDto commentDto;
    private Topic topic;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        topic = new Topic();
        topic.setId(1L);

        comment = new Comment();
        comment.setId(1L);
        comment.setContent("Test Comment");
        comment.setAuthor(user);
        comment.setTopic(topic);
        comment.setCreateTime(new Date());

        commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setContent("Test Comment");
        commentDto.setAuthor_id(1L);
        commentDto.setTopic_id(1L);
    }

    @Test
    void getMessagesForTopic_ShouldReturnCommentDtos() {
        when(commentRepo.findByTopicId(1L)).thenReturn(List.of(comment));

        List<CommentDto> result = commentService.getMessagesForTopic(1L);

        assertEquals(1, result.size());
        assertEquals("Test Comment", result.get(0).getContent());
    }

    @Test
    void createComment_ShouldSaveAndReturnCommentDto() {
        when(topicRepo.findById(1L)).thenReturn(Optional.of(topic));
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(commentRepo.save(any(Comment.class))).thenReturn(comment);

        CommentDto result = commentService.createComment(commentDto);

        assertNotNull(result);
        assertEquals("Test Comment", result.getContent());
    }

    @Test
    void createComment_ShouldThrowException_WhenTopicNotFound() {
        when(topicRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commentService.createComment(commentDto));
    }

    @Test
    void updateComment_ShouldUpdateAndReturnCommentDto() {
        when(commentRepo.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepo.save(any(Comment.class))).thenReturn(comment);

        CommentDto updatedDto = new CommentDto();
        updatedDto.setContent("Updated Comment");

        CommentDto result = commentService.updateComment(1L, updatedDto);

        assertEquals("Updated Comment", result.getContent());
    }

    @Test
    void updateComment_ShouldThrowException_WhenCommentNotFound() {
        when(commentRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commentService.updateComment(1L, commentDto));
    }

    @Test
    void deleteComment_ShouldReturnTrue_WhenCommentExists() {
        when(commentRepo.findById(1L)).thenReturn(Optional.of(comment));

        boolean result = commentService.deleteComment(1L);

        assertTrue(result);
        verify(commentRepo, times(1)).delete(comment);
    }

    @Test
    void deleteComment_ShouldReturnFalse_WhenCommentNotFound() {
        when(commentRepo.findById(1L)).thenReturn(Optional.empty());

        boolean result = commentService.deleteComment(1L);

        assertFalse(result);
    }
}
