package com.example.spotifyplaylistapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    // One user may have many songs and one song can be saved by many users to their playlist.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Song> playlist;

    public void addSong(Song song) {
        this.playlist.add(song);
    }
}
