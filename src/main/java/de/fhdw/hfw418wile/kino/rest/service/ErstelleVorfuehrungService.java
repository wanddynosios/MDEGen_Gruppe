package de.fhdw.hfw418wile.kino.rest.service;

import de.fhdw.hfw418wile.kino.rest.dto.VorfuehrungDTO;
import generated.kino.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErstelleVorfuehrungService {
    @PutMapping("/vorfuehrung")
    public ResponseEntity<VorfuehrungDTO> erstelleVorfuehrung(@RequestBody VorfuehrungDTO vorfuehrungDTO){
        boolean thrown = false;
        try {
            Kino.getInstance().holeVorfuehrung(vorfuehrungDTO.getVorfuehrungNummer());
        } catch (NoSuchElementException e){
           //Alles ok. Wird hier geworfen, wenn es noch keine Vorfuhrungen gibt
            thrown = true;
        } finally {
            if (!thrown){
                vorfuehrungDTO.setMessage("Vorfuehrungsnummer bereits vergeben");
                return ResponseEntity.badRequest().body(vorfuehrungDTO);
            }
        }
        Film film;
        try {
            film = Kino.getInstance().holeFilm(vorfuehrungDTO.getFilmDTO().getFilmName());
        } catch (NoSuchElementException e){
            vorfuehrungDTO.setMessage("Film Unbekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        Saal saal;
        try {
            saal = Kino.getInstance().holeSaal(vorfuehrungDTO.getSaalDTO().getSaalNummer());
        } catch (NoSuchElementException e) {
            vorfuehrungDTO.setMessage("Saalnummer unbekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }

        Integer vorfuehrungsNummer = Kino.getInstance().holeHoehsteVorfuerhungsnummer() + 1;
        try {
            Vorfuehrung.createFresh(
                    film,
                    saal,
                    vorfuehrungsNummer,
                    vorfuehrungDTO.getPreisParkett(),
                    vorfuehrungDTO.getPreisMitte(),
                    vorfuehrungDTO.getPreisLoge(),
                    saal.getAnzahlPlaetzeParkett(),
                    saal.getAnzahlPlaetzeMitte(),
                    saal.getAnzhalPlaetzeLoge(),
                    false
            );
            vorfuehrungDTO.setVorfuehrungNummer(vorfuehrungsNummer);
            vorfuehrungDTO.setFreiePlaetzeParkett(saal.getAnzahlPlaetzeParkett());
            vorfuehrungDTO.setFreiePlaetzeMitte(saal.getAnzahlPlaetzeMitte());
            vorfuehrungDTO.setFreiePlaetzeLoge(saal.getAnzhalPlaetzeLoge());
        } catch (Exception e) {
            e.printStackTrace();
            vorfuehrungDTO.setMessage("Vorfuehrung konnte nicht persistiert werden");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        System.out.println(vorfuehrungDTO);
    return ResponseEntity.accepted().body(vorfuehrungDTO);
    }
}
