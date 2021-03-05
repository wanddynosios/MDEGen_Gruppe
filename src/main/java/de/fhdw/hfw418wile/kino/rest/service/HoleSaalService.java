package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReiheDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SaalDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SitzDTO;
import exceptions.ConstraintViolation;
import generated.kino.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HoleSaalService {

    @ResponseBody
    @GetMapping("/saal/{id}")
    public SaalDTO holeSaal(@PathVariable Integer id) throws PersistenceException, ConstraintViolation {
        Reihe reiheX = Reihe.createFresh(
                KategorieParkett.getInstance(),
                1);
        reiheX.addToSitze(Sitz.createFresh(1, reiheX));
        reiheX.addToSitze(Sitz.createFresh(2, reiheX));
        Reihe reiheXX = Reihe.createFresh(
                KategorieParkett.getInstance(),
                1);
        reiheXX.addToSitze(Sitz.createFresh(1, reiheXX));
        reiheXX.addToSitze(Sitz.createFresh(2, reiheXX));
        Saal.createFresh(1).addToReihen(reiheX);
        Kino.getInstance().holeSaal(1).addToReihen(reiheXX);



        Saal saal =  Kino.getInstance().holeSaal(id);
        SaalDTO saalDTO = new SaalDTO();
        saalDTO.setSaalNummer(saal.getSaalNummer());
        List<Reihe> reihen = saal.getReihen();
        List<ReiheDTO> reiheDTOs = new ArrayList<>();
        for (Reihe reihe : reihen){
            List<Sitz> sitze = reihe.getSitze().stream()
                    .distinct()
                    .collect(Collectors.toList());
            List<SitzDTO> sitzDTOs = new ArrayList<>();
            for (Sitz sitz : sitze){
                sitzDTOs.add(new SitzDTO(sitz.getSitzNummer()));
            }
            reiheDTOs.add(new ReiheDTO(
                    reihe.getReihenNummer(),
                    KategorieDTO.getDTOForKategorie(reihe.getKategorie()),
                    sitzDTOs));
        }
        saalDTO.setReihen(reiheDTOs);
        return saalDTO;
    }
}