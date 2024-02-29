package ru.kolivim.myproject.task.management.system.domain.task;

import jakarta.persistence.*;
import lombok.*;
import ru.kolivim.myproject.task.management.system.api.dto.task.PriorityType;
import ru.kolivim.myproject.task.management.system.api.dto.task.StatusType;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;
import ru.kolivim.myproject.task.management.system.domain.comment.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "task")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "executor_id")
    private UUID executorId;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments = new ArrayList<>();

}


