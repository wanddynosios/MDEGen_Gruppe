package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.VorfuehrungDTO;
import generated.kino.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErstelleVorfuehrungService {
    @PutMapping("/vorfuehrung")
    public ResponseEntity<VorfuehrungDTO> erstelleVorfuehrung(@RequestBody VorfuehrungDTO vorfuehrungDTO) throws PersistenceException {
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
        Film film = null;
        try {
            film = Kino.getInstance().holeFilm(vorfuehrungDTO.getFilmDTO().getFilmName());
        } catch (NoSuchElementException e){
            vorfuehrungDTO.setMessage("Film Unbekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        Saal saal = null;
        try {
            saal = Kino.getInstance().holeSaal(vorfuehrungDTO.getSaalDTO().getSaalNummer());
        } catch (NoSuchElementException e) {
            vorfuehrungDTO.setMessage("Saalnummer unbekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);

        }

        Vorfuehrung.createFresh(
                film,
                saal,
                vorfuehrungDTO.getVorfuehrungNummer(),
                vorfuehrungDTO.getPreisParkett(),
                vorfuehrungDTO.getPreisMitte(),
                vorfuehrungDTO.getPreisLoge(),
                saal.getAnzahlPlaetzeParkett(),
                saal.getAnzahlPlaetzeMitte(),
                saal.getAnzhalPlaetzeLoge(),
                false
        );
        System.out.println(vorfuehrungDTO);
    return ResponseEntity.accepted().body(vorfuehrungDTO);
    }
}
