package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.VorfuehrungDTO;
import generated.kino.Kino;
import generated.kino.NoSuchElementException;
import generated.kino.Vorfuehrung;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuehreVorfuehrungDurch {
    @PutMapping("/vorfuehrung/{vorfuehrungsNummer}")
    public ResponseEntity<VorfuehrungDTO> fuehreVorfuehrungDurch(@PathVariable Integer vorfuehrungsNummer){
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        Vorfuehrung vorfuehrung;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(vorfuehrungsNummer);
        } catch (NoSuchElementException e) {
            vorfuehrungDTO.setMessage("Diese Vorfuehrungsnummer ist nicht bekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        try {
            vorfuehrung.setBereitsVorbei(true);
        } catch (PersistenceException e) {
            vorfuehrungDTO.setMessage("Vorfuehrung konnte nicht durchgefuehrt werden");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        vorfuehrungDTO.setVorfuehrungNummer(vorfuehrungsNummer);
        vorfuehrungDTO.setVorfuehrungVorbei(true);
        return ResponseEntity.accepted().body(vorfuehrungDTO);
    }
}
