package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlaylistController {

    private final UserService userService;

    public PlaylistController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addToPlaylist/{id}")
    public String addToPlaylist(@PathVariable long id) {
        this.userService.addToPlaylist(id);
        return "redirect:/";
    }
}
