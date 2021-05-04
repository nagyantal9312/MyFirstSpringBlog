package com.example.springblog.repository;

import com.example.springblog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByOrderByLastModifiedDateDesc();

    List<BlogPost> findAllByTitleContainingOrderByLastModifiedDateDesc(String title);
}
