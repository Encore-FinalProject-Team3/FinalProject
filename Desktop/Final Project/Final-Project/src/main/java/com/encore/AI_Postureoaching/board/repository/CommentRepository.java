package com.encore.AI_Postureoaching.board.repository;

import com.encore.AI_Postureoaching.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
