package com.example.springblog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractReaction {

    private boolean reactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blogger blogger;

}
