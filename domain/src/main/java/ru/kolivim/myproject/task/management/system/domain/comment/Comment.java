package ru.kolivim.myproject.task.management.system.domain.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;
import ru.kolivim.myproject.task.management.system.domain.task.Task;

import java.util.UUID;
@Table (name = "comment")
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Column (name = "author_id")
    private UUID authorId;

    @Column (name = "comment_text")
    private String commentText;

    @ManyToOne
    private Task task;

}
