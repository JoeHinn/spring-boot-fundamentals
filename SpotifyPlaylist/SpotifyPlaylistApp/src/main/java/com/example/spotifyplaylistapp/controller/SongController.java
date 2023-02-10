package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    private final AuthService authService;
    private final SongService songService;

    @Autowired
    public SongController(AuthService authService, SongService songService) {
        this.authService = authService;
        this.songService = songService;
    }

    @ModelAttribute("addSongDTO")
    public AddSongDto addSongDTO() {
        return new AddSongDto();
    }

    @GetMapping("/addSong")
    public String addSong() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping("/addSong")
    public String addSong (@Valid AddSongDto addSongDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.songService.addSong(addSongDTO)) {
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/addSong";
        }

        return "redirect:/home";
    }
}
