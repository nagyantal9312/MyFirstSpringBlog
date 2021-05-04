package com.example.springblog.repository;

import com.example.springblog.model.BlogPostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostReactionRepository extends JpaRepository<BlogPostReaction, Long> {

    int countBlogPostReactionByBlogPost_IdAndReactionType(long blogPostId, boolean type);

    boolean existsByBlogPost_IdAndAndBlogger_UsernameAndAndReactionType(long blogPostId, String username, boolean type);

    BlogPostReaction findByBlogPost_IdAndBlogger_UsernameAndReactionType(long blogPostId, String username, boolean type);
}
