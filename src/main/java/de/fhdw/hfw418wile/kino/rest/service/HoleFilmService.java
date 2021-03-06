package de.fhdw.hfw418wile.kino.rest.service;

import de.fhdw.hfw418wile.kino.rest.dto.FilmDTO;
import generated.kino.Film;
import generated.kino.Kino;
import generated.kino.NoSuchElementException;
import generated.kino.proxies.FilmProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class HoleFilmService {
    @GetMapping("/film/all")
    public ResponseEntity<Set<FilmDTO>> holeFilme(){
        Map<Integer, FilmProxy> filmCache = Kino.getInstance().getFilmCache();
        List<Film> filme = new ArrayList<>();
        filmCache.forEach((integer, filmProxy) -> filme.add(filmProxy.getTheObject()));
        Set<FilmDTO> filmDTOs = new HashSet<>();
        for (Film film : filme){
            filmDTOs.add(new FilmDTO(film.getFilmName()));
        }
        return ResponseEntity.accepted().body(filmDTOs);
    }

    @GetMapping("/film/{filmName}")
    public ResponseEntity<FilmDTO> holeFilm(@PathVariable String filmName){
        try {
            Film film = Kino.getInstance().holeFilm(filmName);
        } catch (NoSuchElementException e) {
            FilmDTO filmDTO = new FilmDTO();
            filmDTO.setMessage("FilmName unbekannt");
            return ResponseEntity.badRequest().body(filmDTO);
        }
        return ResponseEntity.accepted().body(new FilmDTO(filmName));
    }
}
