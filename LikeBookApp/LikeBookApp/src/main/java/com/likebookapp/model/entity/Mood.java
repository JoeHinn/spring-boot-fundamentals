package com.likebookapp.model.entity;

import com.likebookapp.model.entity.enums.MoodType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "moods")
public class Mood extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MoodType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood(MoodType moodType) {
        this.name = moodType;
    }
}
