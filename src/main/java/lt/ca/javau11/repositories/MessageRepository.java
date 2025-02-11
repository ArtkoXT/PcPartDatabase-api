package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTopicId(Long topicId);
}
