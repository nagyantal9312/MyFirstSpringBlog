package com.example.springblog.service;

import com.example.springblog.model.Blogger;
import com.example.springblog.model.Role;
import com.example.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public BloggerService(BloggerRepository bloggerRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.bloggerRepository = bloggerRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    public void registerBlogger(Blogger blogger) {

        File file = new File("src/main/resources/static/images/defaultprofile.png");

        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = Base64
                .getEncoder()
                .encodeToString(fileContent);

        blogger.setPhoto(encodedString);


        blogger.setEnabled(true);
        Role role = roleService.findByName("USER");
        blogger.setRoles(Arrays.asList(role));
        blogger.setPassword(passwordEncoder.encode(blogger.getPassword()));
        bloggerRepository.save(blogger);
    }


    public void editBlogger(Blogger blogger) {

        Blogger bg = bloggerRepository.findByUsername(blogger.getUsername());
        blogger.setPhoto(bg.getPhoto());
        blogger.setBlogPostReactions(bg.getBlogPostReactions());
        blogger.setCommentReactions(bg.getCommentReactions());
        if (blogger.getPassword() == null || blogger.getPassword().isEmpty()) {
            blogger.setPassword(bg.getPassword());
        } else {
            blogger.setPassword(passwordEncoder.encode(blogger.getPassword()));
        }
        bloggerRepository.save(blogger);
    }


    public Blogger banBlogger(Blogger blogger) {

        if (blogger.isEnabled()) {
            blogger.setEnabled(false);
        } else {
            blogger.setEnabled(true);
        }
        return bloggerRepository.save(blogger);
    }


    public void uploadPhoto(Blogger blogger, MultipartFile image) {

        byte[] fileContent = new byte[0];
        try {
            fileContent = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = Base64
                .getEncoder()
                .encodeToString(fileContent);

        blogger.setPhoto(encodedString);
        bloggerRepository.save(blogger);
    }


    public Blogger findByUsername(String username) {
        return bloggerRepository.findByUsername(username);
    }

    public void deleteBlogger(String username) {
        bloggerRepository.deleteByUsername(username);
    }





}
