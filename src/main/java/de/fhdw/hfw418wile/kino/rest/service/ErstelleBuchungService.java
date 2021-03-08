package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.*;
import exceptions.ConstraintViolation;
import generated.kino.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
public class ErstelleBuchungService {
    @PutMapping("/buchung")
    public ResponseEntity<BuchungDTO> buche(@RequestBody BuchungDTO buchungDTO){
        boolean thrown = false;
        try {
            Kino.getInstance().holeBuchung(buchungDTO.getBuchungsNummer());
        } catch (NoSuchElementException e) {
            //alles ok!
            thrown = true;
        } finally {
            if (!thrown){
                buchungDTO.setMessage("Diese Buchungsnummer ist bereits vergeben!");
                return ResponseEntity.badRequest().body(buchungDTO);
            }
        }
        Vorfuehrung vorfuehrung = null;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(buchungDTO.getVorfuehrungDTO().getVorfuehrungNummer());
        } catch (NoSuchElementException e) {
            buchungDTO.setMessage("Vorfuehrungsnummer unbekannt");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        Buchung buchung = null;
        try {
            buchung = Buchung.createFresh(
                    buchungDTO.getBuchungsNummer(),
                    vorfuehrung
            );
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Buchung konnte nicht persisitert werden");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        Resevierung resevierung = null;
        try {
            resevierung = Kino.getInstance().holeReservierung(buchungDTO.getReservierungDTO().getName());
        } catch (NoSuchElementException e) {
            buchungDTO.setMessage("Die angegebene Reservierung konnte nicht gefunden werden");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        for (BuchungseinheitDTO buchungseinheitDTO : buchungDTO.getBuchungseinheitDTOs()){
            int reihenNummer = buchungseinheitDTO.getSitzDTO().getReiheDTO().getReihenNummer();
            int sitzNummer = buchungseinheitDTO.getSitzDTO().getSitzNummer();
            Sitz sitz = null;
            try {
                sitz = vorfuehrung.getSaal().getReihen().get(reihenNummer-1).getSitze().get(sitzNummer-1);
            } catch (PersistenceException e) {
                buchungDTO.setMessage("Der/die gewuenschte Saal/Reihe/Sitz konnten nicht gefunden werden");
                return ResponseEntity.badRequest().body(buchungDTO);
            } catch (ArrayIndexOutOfBoundsException e){
                buchungDTO.setMessage("Die/der gewuenschte Reihe/Sitz existiert nicht");
                return ResponseEntity.badRequest().body(buchungDTO);
            }
            BuchungsEinheit buchungsEinheit = null;
            try {
                buchungsEinheit = BuchungsEinheit.createFresh(sitz);
            } catch (PersistenceException e) {
                buchungDTO.setMessage("Eine Buchungseinheit konnte nicht persisitert werden");
                return ResponseEntity.badRequest().body(buchungDTO);
            }
            try {
                buchung.addToBuchungsEinheiten(buchungsEinheit);
            } catch (PersistenceException e) {
                buchungDTO.setMessage("Die Buchung konnte nicht erfolgreich persisitert werden (addToBuchungsEinheiten())");
                return ResponseEntity.badRequest().body(buchungDTO);
            }
        }
        try {
            vorfuehrung.addToBuchungen(buchung);
        } catch (ConstraintViolation constraintViolation) {
            buchungDTO.setMessage("Buchung konnte nicht zur Vorfuehrung hinzugefuegt werden." +
                    "Irgendetwas ist banane");
            return ResponseEntity.badRequest().body(buchungDTO);
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Die Buchung konnte nicht persisitert werden (addToVorfuehrungen())");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        try {
            resevierung.setIstBereitsEingeloest(true);
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Der Status der Reservierung konnte nicht geaendert werden");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        ResponseEntity<ReservierungDTO> reservierungDTOResponse = new HoleReservierungService().holeReservierung(resevierung.getName());
        if (!reservierungDTOResponse.getStatusCode().equals(HttpStatus.ACCEPTED)){
            buchungDTO.setMessage("Die zugehoerige Reservierung konnte nicht gefunden werden: "+reservierungDTOResponse.getBody().getMessage());
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        ReservierungDTO reservierungDTO = reservierungDTOResponse.getBody();
        buchungDTO.setReservierungDTO(reservierungDTO);
        ResponseEntity<VorfuehrungDTO> vorfuehrungDTOResponse = new HoleVorfuehrungService().holeVorfuehrung(vorfuehrung.getVorfuehrungsNummer());
        if (vorfuehrungDTOResponse.getStatusCode() != HttpStatus.ACCEPTED){
            buchungDTO.setMessage("Die zugehoerige Vorfuehrung konnte nicht gefunden werden: "+vorfuehrungDTOResponse.getBody().getMessage());
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        VorfuehrungDTO vorfuehrungDTO = vorfuehrungDTOResponse.getBody();
        buchungDTO.setVorfuehrungDTO(vorfuehrungDTO);

        return ResponseEntity.accepted().body(buchungDTO);
    }

    @GetMapping("/buchungtest")
    @ResponseBody
    public BuchungDTO buchungDTO(){
        BuchungDTO buchungDTO = new BuchungDTO();
        buchungDTO.setBuchungsNummer(1);
        Set<BuchungseinheitDTO> buchungseinheitDTOSet = new HashSet<>();
        ReiheDTO reiheDTO = new ReiheDTO();
        reiheDTO.setReihenNummer(1);
        SitzDTO sitzDTO1 = new SitzDTO();
        sitzDTO1.setSitzNummer(1);
        sitzDTO1.setReiheDTO(reiheDTO);
        SitzDTO sitzDTO2 = new SitzDTO();
        sitzDTO2.setSitzNummer(2);
        sitzDTO2.setReiheDTO(reiheDTO);
        BuchungseinheitDTO buchungseinheitDTO1 = new BuchungseinheitDTO();
        buchungseinheitDTO1.setSitzDTO(sitzDTO1);
        BuchungseinheitDTO buchungseinheitDTO2 = new BuchungseinheitDTO();
        buchungseinheitDTO2.setSitzDTO(sitzDTO2);
        buchungseinheitDTOSet.add(buchungseinheitDTO1);
        buchungseinheitDTOSet.add(buchungseinheitDTO2);
        buchungDTO.setBuchungseinheitDTOs(buchungseinheitDTOSet);
        return buchungDTO;
    }
}
