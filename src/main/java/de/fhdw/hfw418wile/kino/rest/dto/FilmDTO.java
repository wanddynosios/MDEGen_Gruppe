package de.fhdw.hfw418wile.kino.rest.dto;

import generated.kino.Film;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmDTO {
    private String filmName;

    public FilmDTO(String filmName) {
        this.filmName = filmName;
    }
}
