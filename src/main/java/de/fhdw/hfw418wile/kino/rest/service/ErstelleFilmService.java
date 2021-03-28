package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.FilmDTO;
import generated.kino.Film;
import generated.kino.Kino;
import generated.kino.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErstelleFilmService {
    @PutMapping("/film")
    public ResponseEntity<FilmDTO> erstelleFilm(@RequestBody FilmDTO filmDTO){
        if (filmDTO.getFilmName() == null){
            filmDTO.setMessage("Kein Filmname angegeben");
            return ResponseEntity.badRequest().body(filmDTO);
        }
        if (Kino.getInstance().filmExistiert(filmDTO.getFilmName())){
            filmDTO.setMessage("Film bereits vorhanden");
            return ResponseEntity.badRequest().body(filmDTO);
        }
        try {
            Film.createFresh(filmDTO.getFilmName());
        } catch (PersistenceException e) {
            filmDTO.setMessage("Film "+filmDTO.toString()+" konnte nicht persistiert werden");
            return ResponseEntity.badRequest().body(filmDTO);
        }
        return ResponseEntity.accepted().body(filmDTO);
    }
}
