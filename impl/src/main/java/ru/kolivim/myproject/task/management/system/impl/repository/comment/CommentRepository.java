package ru.kolivim.myproject.task.management.system.impl.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.kolivim.myproject.task.management.system.domain.comment.Comment;
import ru.kolivim.myproject.task.management.system.impl.repository.base.BaseRepository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID>, JpaSpecificationExecutor<Comment>, BaseRepository<Comment> {

}
