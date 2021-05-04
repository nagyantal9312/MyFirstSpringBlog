package com.example.springblog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<BlogPost> blogPosts;
}
