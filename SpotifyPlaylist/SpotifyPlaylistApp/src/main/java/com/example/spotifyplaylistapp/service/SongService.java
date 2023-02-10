package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SongService {

    private final SongRepository songRepository;
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public SongService(SongRepository songRepository, StyleRepository styleRepository, UserRepository userRepository, AuthService authService) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public boolean addSong(AddSongDto addSongDto){
        Song song = new Song();
        song.setPerformer(addSongDto.getPerformer());
        song.setTitle(addSongDto.getTitle());
        song.setReleaseDate(addSongDto.getReleaseDate());
        song.setDuration(addSongDto.getDuration());

        Style style = this.styleRepository.findByName(StyleName.valueOf(addSongDto.getStyle()));
        song.setStyle(style);

        this.songRepository.save(song);
        return true;
    }

    public List<Song> getPopSongs() {
        Style style = this.styleRepository.findByName(StyleName.valueOf("POP"));
        return this.songRepository.findByStyle(style);
    }

    public List<Song> getRockSongs() {
        Style style = this.styleRepository.findByName(StyleName.valueOf("ROCK"));
        return this.songRepository.findByStyle(style);
    }

    public List<Song> getJazzSongs() {
        Style style = this.styleRepository.findByName(StyleName.valueOf("JAZZ"));
        return this.songRepository.findByStyle(style);
    }
}
