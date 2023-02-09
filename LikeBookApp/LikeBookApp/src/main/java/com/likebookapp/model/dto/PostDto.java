package com.likebookapp.model.dto;

import com.likebookapp.model.entity.enums.MoodType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    @NotNull
    @Size(min = 2, max = 150)
    private String content;

    @NotNull
    private MoodType moodType;
}
