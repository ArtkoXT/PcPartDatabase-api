package lt.ca.javau11.services;

import lt.ca.javau11.entities.Comment;
import lt.ca.javau11.entities.DTOs.TopicDto;
import lt.ca.javau11.entities.DTOs.TopicRequest;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepo;

    @Mock
    private CommentRepository commentRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private TopicService topicService;

    private Topic topic;
    private User user;
    private Comment comment;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        topic = new Topic();
        topic.setId(1L);
        topic.setTitle("Test Topic");
        topic.setAuthor(user);

        comment = new Comment();
        comment.setId(1L);
        comment.setAuthor(user);
        comment.setCreateTime(new Date());
        comment.setTopic(topic);
    }

    @Test
    void testAddTopic() {
        when(topicRepo.save(any(Topic.class))).thenReturn(topic);
        Topic result = topicService.addTopic(topic);
        assertNotNull(result);
        assertEquals("Test Topic", result.getTitle());
    }

    @Test
    void testCreateTopic() {
        TopicRequest topicRequest = new TopicRequest();
        topicRequest.setAuthor_id(1L);
        topicRequest.setTopic(topic);
        topicRequest.setComment(comment);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(topicRepo.save(any(Topic.class))).thenReturn(topic);
        when(commentRepo.save(any(Comment.class))).thenReturn(comment);

        Topic result = topicService.createTopic(topicRequest);
        assertNotNull(result);
        assertEquals("Test Topic", result.getTitle());
    }

    @Test
    void testGetTopic_Found() {
        when(topicRepo.findById(1L)).thenReturn(Optional.of(topic));
        TopicDto result = topicService.getTopic(1L);
        assertNotNull(result);
        assertEquals("Test Topic", result.getTitle());
        assertEquals("testUser", result.getAuthor_name());
    }

    @Test
    void testGetTopic_NotFound() {
        when(topicRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> topicService.getTopic(1L));
    }

    @Test
    void testGetAllTopics() {
        when(topicRepo.findAll()).thenReturn(List.of(topic));
        List<TopicDto> result = topicService.getAllTopics();
        assertEquals(1, result.size());
        assertEquals("Test Topic", result.get(0).getTitle());
    }

    @Test
    void testDeleteTopic_Success() {
        when(topicRepo.findById(1L)).thenReturn(Optional.of(topic));
        boolean result = topicService.deleteTopic(1L);
        assertTrue(result);
        verify(topicRepo, times(1)).delete(topic);
    }

    @Test
    void testDeleteTopic_NotFound() {
        when(topicRepo.findById(1L)).thenReturn(Optional.empty());
        boolean result = topicService.deleteTopic(1L);
        assertFalse(result);
        verify(topicRepo, never()).delete(any());
    }
}
