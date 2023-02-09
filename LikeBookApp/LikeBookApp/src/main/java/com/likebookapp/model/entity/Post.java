package com.likebookapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    @Column(nullable = false)
    private String content;

    // One post can have only one user and one user may have many posts
    @ManyToOne
    private User user;

    // One user may like many posts and one post can be liked by many users.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userLikes;

    // One post has one mood and one mood can have many posts
    @ManyToOne
    private Mood mood;

    public void setUsersLikes(User user) {
        this.userLikes.add(user);
    }
}
