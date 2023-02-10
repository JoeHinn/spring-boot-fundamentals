package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;
    private final LoggedUser loggedUser;
    private final SongService songService;
    private final UserService userService;

    @Autowired
    public HomeController(AuthService authService, LoggedUser loggedUser, SongService songService, UserService userService) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        if (!this.authService.isLoggedIn()){
            return "index";
        }

        List<Song> popSongs = this.songService.getPopSongs();
        model.addAttribute("popSongs", popSongs);

        List<Song> rockSongs = this.songService.getRockSongs();
        model.addAttribute("rockSongs", rockSongs);

        List<Song> jazzSongs = this.songService.getJazzSongs();
        model.addAttribute("jazzSongs", jazzSongs);

        List<Song> userSongs = this.userService.getUserSongs();
        model.addAttribute("userSongs", userSongs);



        return "home";
    }
}
