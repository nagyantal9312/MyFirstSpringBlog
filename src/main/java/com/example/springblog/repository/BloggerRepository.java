package com.example.springblog.repository;

import com.example.springblog.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {

    Blogger findByUsername(String username);

    void deleteByUsername(String username);

}
