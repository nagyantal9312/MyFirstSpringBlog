package com.example.springblog.repository;

import com.example.springblog.model.Comment;
import com.example.springblog.model.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    int countCommentReactionByComment_IdAndReactionType(long commentId, boolean type);

    CommentReaction findByComment_IdAndBlogger_UsernameAndReactionType(long commentId, String username, boolean type);

}
