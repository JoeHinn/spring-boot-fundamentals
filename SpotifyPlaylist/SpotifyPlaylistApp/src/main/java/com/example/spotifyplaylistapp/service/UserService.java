package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final SongRepository songRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public UserService(SongRepository songRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public void addToPlaylist(long id) {
        Optional<Song> byId = this.songRepository.findById(id);
        Song song = byId.get();

        Optional<User> byIdUser = this.userRepository.findById(this.loggedUser.getId());
        User user = byIdUser.get();
        user.addSong(song);

    }

    public List<Song> getUserSongs() {
        Optional<User> byIdUser = this.userRepository.findById(this.loggedUser.getId());
        User user = byIdUser.get();
        return user.getPlaylist();
    }
}
