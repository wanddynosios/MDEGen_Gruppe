package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReiheDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SaalDTO;
import de.fhdw.hfw418wile.kino.rest.dto.SitzDTO;
import exceptions.ConstraintViolation;
import generated.kino.NoSuchElementException;
import generated.kino.*;
import generated.kino.proxies.SaalProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class HoleSaalService {
    @GetMapping("/saal/all")
    public ResponseEntity<Set<SaalDTO>> holeSaele(){
        Map<Integer, SaalProxy> saalCache = Kino.getInstance().getSaalCache();
        Set<Saal> saele = new HashSet<>();
        saalCache.forEach((integer, saalProxy) -> saele.add(saalProxy.getTheObject()));
        Set<SaalDTO> saalDTOs = new HashSet<>();
        for (Saal saal : saele){
            SaalDTO saalDTO = new SaalDTO();
            saalDTO.setSaalNummer(saal.getSaalNummer());
            saalDTOs.add(saalDTO);
        }
        return ResponseEntity.accepted().body(saalDTOs);

    }

    @GetMapping("/saal/{id}")
    public ResponseEntity<SaalDTO> holeSaal(@PathVariable Integer id){
//        Reihe reiheX = Reihe.createFresh(
//                KategorieParkett.getInstance(),
//                1);
//        reiheX.addToSitze(Sitz.createFresh(1, reiheX));
//        reiheX.addToSitze(Sitz.createFresh(2, reiheX));
//        Reihe reiheXX = Reihe.createFresh(
//                KategorieParkett.getInstance(),
//                1);
//        reiheXX.addToSitze(Sitz.createFresh(1, reiheXX));
//        reiheXX.addToSitze(Sitz.createFresh(2, reiheXX));
//        Saal.createFresh(1).addToReihen(reiheX);
//        try {
//            Kino.getInstance().holeSaal(1).addToReihen(reiheXX);
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }


        Saal saal;
        try {
            saal = Kino.getInstance().holeSaal(id);
        } catch (NoSuchElementException e) {
            SaalDTO saalDTO = new SaalDTO();
            saalDTO.setMessage("Saalnummer unbekannt");
            return ResponseEntity.badRequest().body(saalDTO);
        }
        SaalDTO saalDTO = new SaalDTO();
        saalDTO.setSaalNummer(saal.getSaalNummer());
        List<Reihe> reihen;
        try {
            reihen = saal.getReihen();
        } catch (PersistenceException e) {
            saalDTO.setMessage("Persistenzfehler bei den Reihen des Saals "+saalDTO.toString());
            return ResponseEntity.badRequest().body(saalDTO);
        }
        List<ReiheDTO> reiheDTOs = new ArrayList<>();
        reihen.sort(Comparator.comparing(Reihe::getReihenNummer));
        for (Reihe reihe : reihen){
            List<Sitz> sitze;
            try {
                sitze = reihe.getSitze().stream()
                        .distinct()
                        .collect(Collectors.toList());
            } catch (PersistenceException e) {
                saalDTO.setMessage("Persistenzfehler bei den Sitzen der Reihe "+reihe.toString());
                return ResponseEntity.badRequest().body(saalDTO);
            }
            List<SitzDTO> sitzDTOs = new ArrayList<>();
            for (Sitz sitz : sitze){
                sitzDTOs.add(new SitzDTO(sitz.getSitzNummer()));
            }
            try {
                reiheDTOs.add(new ReiheDTO(
                        reihe.getReihenNummer(),
                        KategorieDTO.getDTOForKategorie(reihe.getKategorie()),
                        sitzDTOs));
            } catch (PersistenceException e) {
                saalDTO.setMessage("Reihe "+reihe.toString()+" hat keine (valide) Kategorie");
                return ResponseEntity.badRequest().body(saalDTO);
            }
        }
        saalDTO.setReihen(reiheDTOs);
        return ResponseEntity.accepted().body(saalDTO);
    }
}