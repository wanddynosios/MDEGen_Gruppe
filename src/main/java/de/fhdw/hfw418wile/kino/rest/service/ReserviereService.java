package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.FilmDTO;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReservierungDTO;
import de.fhdw.hfw418wile.kino.rest.dto.VorfuehrungDTO;
import exceptions.ConstraintViolation;
import generated.kino.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReserviereService {
    @PutMapping("/reserviere")
    public ResponseEntity<ReservierungDTO> reserviere(@RequestBody ReservierungDTO reservierungDTO) throws PersistenceException, NichtGenugPlaetzeException, ConstraintViolation {
//        Vorfuehrung vorfuehrungX = Vorfuehrung.createFresh(
//                Film.createFresh("STARWARS"),
//                Saal.createFresh(1),
//                5669,
//                4,
//                5,
//                32,
//                55,
//                12,
//                9,
//                false
//        );

        Integer vorfuherungNummer = reservierungDTO.getVorfuehrungDTO().getVorfuehrungNummer();
        Vorfuehrung vorfuehrung = null;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(vorfuherungNummer);
            System.out.println("V: "+vorfuehrung);
        } catch (NoSuchElementException e) {
            reservierungDTO.setMessage("Vorfuehrungsnummer unbekannt");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }
        Resevierung reservierung = Resevierung.createFresh(
                KategorieDTO.getKategroieForDTO(reservierungDTO.getKategorieDTO()),
                reservierungDTO.getName(),
                reservierungDTO.getAnzahlPlaetze(),
                false,
                vorfuehrung
        );
        vorfuehrung.reserviere(reservierung);
        System.out.println(vorfuehrung);
        return ResponseEntity.accepted().body(reservierungDTO);
    }
    @ResponseBody
    @GetMapping("/reserviere")
    public ReservierungDTO getPrototype(){
        ReservierungDTO reservierungDTO = new ReservierungDTO();
        reservierungDTO.setKategorieDTO(KategorieDTO.KategorieParkett);
        reservierungDTO.setName("Mustermann");
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmName("STARWARS");
        vorfuehrungDTO.setFilmDTO(filmDTO);
        vorfuehrungDTO.setVorfuehrungNummer(5669);
        vorfuehrungDTO.setFreiePlaetzeParkett(65);
        reservierungDTO.setVorfuehrungDTO(vorfuehrungDTO);
        reservierungDTO.setAnzahlPlaetze(3);
        return reservierungDTO;
    }
}
