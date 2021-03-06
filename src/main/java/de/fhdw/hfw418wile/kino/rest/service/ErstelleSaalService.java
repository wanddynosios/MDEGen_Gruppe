package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReiheDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SaalDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SitzDTO;
import generated.kino.Reihe;
import generated.kino.Saal;
import generated.kino.Sitz;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ErstelleSaalService {
    @ResponseBody
    @PutMapping("/saal")
    public SaalDTO erstelleSaal(@RequestBody SaalDTO saalDTO) throws PersistenceException {
        System.out.println(saalDTO);
        if (saalDTO.getReihen() == null)
            System.out.println("ERROR keine Reihen");
        Saal saal = Saal.createFresh(saalDTO.getSaalNummer());
        for (ReiheDTO reiheDTO : saalDTO.getReihen()){
            Reihe reihe = Reihe.createFresh(
                    KategorieDTO.getKategroieForDTO(reiheDTO.getKategorieDTO()),
                    reiheDTO.getReihenNummer());
            List<Sitz> sitze = new ArrayList<>();
            for (SitzDTO sitzDTO : reiheDTO.getSitze()){
                sitze.add(Sitz.createFresh(sitzDTO.getSitzNummer(), reihe));
            }
            saal.addToReihen(reihe);
        }
        System.out.println(saal.getSaalNummer());
        System.out.println(saal.getReihen().get(0).toString());
        System.out.println(saal.getReihen().get(1).toString());
        return saalDTO;

    }


}
