package com.example.springblog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger extends AbstractAuditableEntity<String> implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = this.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        /*Locked indicates an account has been automatically suspended due to invalid login attempts.
        Usually the passage of time or (less often) requesting manual unlocking is required to release it. */
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        /* Disabled indicates an account has been administratively or automatically disabled for some reason.
       Usually some action is required to release it. */
        return this.enabled;
    }





}
