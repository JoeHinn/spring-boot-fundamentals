package com.likebookapp.init;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.enums.MoodType;
import com.likebookapp.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoodInit implements CommandLineRunner {
    private final MoodRepository moodRepository;

    @Autowired
    public MoodInit(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) {
        if (this.moodRepository.count() == 0){
            List<Mood> moods = Arrays.stream(MoodType.values())
                    .map(Mood::new)
                    .collect(Collectors.toList());

            this.moodRepository.saveAllAndFlush(moods);
        }
    }
}
