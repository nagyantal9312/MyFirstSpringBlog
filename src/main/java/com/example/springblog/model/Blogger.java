package com.example.springblog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger extends AbstractAuditableEntity<String>{

    @Id
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private String password;

    @NotBlank
    private String name;

    @Column(length = 10485760)
    private String photo;

    @PastOrPresent
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogPostReaction> blogPostReactions;

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentReaction> commentReactions;




}
