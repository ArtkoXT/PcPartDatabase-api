package lt.ca.javau11.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Date createTime;

    @Column(nullable=false, length=1000)
    private String content;

    @ManyToOne(optional=false)
    private Topic topic;

    public Message(String content, Topic topic) {
        this.createTime = new Date();
        this.content = content;
        this.topic = topic;
    }
}
