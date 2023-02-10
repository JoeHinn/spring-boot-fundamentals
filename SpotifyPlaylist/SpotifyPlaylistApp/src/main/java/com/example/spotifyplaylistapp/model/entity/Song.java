package com.example.spotifyplaylistapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song extends BaseEntity{

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    private Integer duration;

    private LocalDate releaseDate;

    // One song has one style and one style can have many songs
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Style style;

    private String toMinutes() {
        int minutes = this.duration / 60;
        int seconds = this.duration % 60;

        return String.format("%d:%d", minutes, seconds);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s min)", this.performer, this.title, this.toMinutes());
    }
}
