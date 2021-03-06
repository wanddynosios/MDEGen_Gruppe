package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReiheDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SaalDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SitzDTO;
import generated.kino.Reihe;
import generated.kino.Saal;
import generated.kino.Sitz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ErstelleSaalService {
    @PutMapping("/saal")
    public ResponseEntity<SaalDTO> erstelleSaal(@RequestBody SaalDTO saalDTO) {
        System.out.println(saalDTO);
        if (saalDTO.getReihen() == null)
            System.out.println("ERROR keine Reihen");
        Saal saal = null;
        try {
            saal = Saal.createFresh(saalDTO.getSaalNummer());
        } catch (PersistenceException e) {
            saalDTO.setMessage("Saal nicht valide oder SaalNummer bereits vergeben");
            return ResponseEntity.badRequest().body(saalDTO);
        }
        for (ReiheDTO reiheDTO : saalDTO.getReihen()){
            Reihe reihe = null;
            try {
                reihe = Reihe.createFresh(
                        KategorieDTO.getKategroieForDTO(reiheDTO.getKategorieDTO()),
                        reiheDTO.getReihenNummer());
            } catch (PersistenceException e) {
                saalDTO.setMessage("Reihe "+reiheDTO.toString()+" konnte nicht persistiert werden");
                return ResponseEntity.badRequest().body(saalDTO);
            }
            List<Sitz> sitze = new ArrayList<>();
            for (SitzDTO sitzDTO : reiheDTO.getSitze()){
                try {
                    sitze.add(Sitz.createFresh(sitzDTO.getSitzNummer(), reihe));
                    saal.addToReihen(reihe);
                } catch (PersistenceException e) {
                    sitzDTO.setMessage("Sitz "+sitzDTO.toString()+" oder Reihe "+reiheDTO.toString()+" nicht valide");
                    return ResponseEntity.badRequest().body(saalDTO);
                }
            }
        }
        return ResponseEntity.accepted().body(saalDTO);

    }


}
