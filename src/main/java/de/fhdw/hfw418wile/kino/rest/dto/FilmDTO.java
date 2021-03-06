package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends DTO{
    private String filmName;

    public void setFilmName(String filmName) {
        this.filmName = filmName.replace(" ", "_");
    }

    public FilmDTO(String filmName) {
        this.filmName = filmName;
    }
}
