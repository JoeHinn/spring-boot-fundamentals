package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StyleInit implements CommandLineRunner {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleInit(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) {

        if (this.styleRepository.count() <= 0) {
            List<Style> styles = Arrays.stream(StyleName.values())
                    .map(Style::new)
                    .collect(Collectors.toList());

            this.styleRepository.saveAllAndFlush(styles);
        }
    }
}
