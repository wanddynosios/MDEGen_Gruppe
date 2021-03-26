package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReservierungDTO;
import exceptions.ConstraintViolation;
import generated.kino.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ErstelleReservierungService {
    @PutMapping("/reservierung")
    public ResponseEntity<ReservierungDTO> reserviere(@RequestBody ReservierungDTO reservierungDTO){
        Integer vorfuherungNummer = reservierungDTO.getVorfuehrungDTO().getVorfuehrungNummer();
        Vorfuehrung vorfuehrung;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(vorfuherungNummer);
        } catch (NoSuchElementException e) {
            reservierungDTO.setMessage("Vorfuehrungsnummer unbekannt");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }
        boolean thrown = false;
        try {
            Kino.getInstance().holeReservierung(reservierungDTO.getName()+"_"+reservierungDTO.getVorfuehrungDTO().getVorfuehrungNummer());
        } catch (NoSuchElementException e) {
            thrown = true;
            //alles ok
        }
        if (!thrown){
            reservierungDTO.setMessage("Reservierung unter diesem Namen fuer diesen Film bereits vorhanden");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }
        if (vorfuehrung.getBereitsVorbei()){
            reservierungDTO.setMessage("Die Vorfuehrung ist vorbei. Eine Resevierung ist nicht mehr moeglich");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }


        try {
            vorfuehrung.reserviere(KategorieDTO.getKategorieForDTO(reservierungDTO.getKategorieDTO()), reservierungDTO.getAnzahlPlaetze());
        } catch (NichtGenugPlaetzeException e) {
            reservierungDTO.setMessage("Es gibt nicht genug Plaetze in der gewuenschten Kategorie");
            return ResponseEntity.badRequest().body(reservierungDTO);
        } catch (PersistenceException e) {
            reservierungDTO.setMessage("Reservierung konnte leider nicht persistiert werden");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }


        Resevierung reservierung;
        try {
            reservierung = Resevierung.createFresh(
                    KategorieDTO.getKategorieForDTO(reservierungDTO.getKategorieDTO()),
                    reservierungDTO.getName()+"_"+reservierungDTO.getVorfuehrungDTO().getVorfuehrungNummer(),
                    reservierungDTO.getAnzahlPlaetze(),
                    false,
                    vorfuehrung
            );
        } catch (PersistenceException e) {
            reservierungDTO.setMessage("Reservierung konnte nicht persistiert werden");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }
        try {
            reservierung.getVorfuehrung().addToReservierungen(reservierung); //TODO
        } catch (ConstraintViolation constraintViolation) {
            reservierungDTO.setMessage("Reservierung konnte nicht der Vorfuehrung hinzugefuegt werden." +
                    " Irgendetwas ist banane ");
            return ResponseEntity.badRequest().body(reservierungDTO);
        } catch (PersistenceException e) {
            reservierungDTO.setMessage("Reservierung konnte leider nicht in der Vorfuehrung persistiert werden");
            return ResponseEntity.badRequest().body(reservierungDTO);
        }
        System.out.println(vorfuehrung);
        return ResponseEntity.accepted().body(reservierungDTO);
    }
//    @ResponseBody
//    @GetMapping("/reserviere")
//    public ReservierungDTO getPrototype(){
//        ReservierungDTO reservierungDTO = new ReservierungDTO();
//        reservierungDTO.setKategorieDTO(KategorieDTO.KategorieParkett);
//        reservierungDTO.setName("Mustermann");
//        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
//        FilmDTO filmDTO = new FilmDTO();
//        filmDTO.setFilmName("STARWARS");
//        vorfuehrungDTO.setFilmDTO(filmDTO);
//        vorfuehrungDTO.setVorfuehrungNummer(5669);
//        vorfuehrungDTO.setFreiePlaetzeParkett(65);
//        reservierungDTO.setVorfuehrungDTO(vorfuehrungDTO);
//        reservierungDTO.setAnzahlPlaetze(3);
//        return reservierungDTO;
//    }
}
