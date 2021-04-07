package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.*;
import exceptions.ConstraintViolation;
import generated.kino.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class ErstelleBuchungService {
    @PutMapping("/buchung")
    public ResponseEntity<BuchungDTO> buche(@RequestBody BuchungDTO buchungDTO){
        boolean thrown = false;
        Integer buchungsNummer = Kino.getInstance().holeHoehsteBuchungsnummer() + 1;
        try {
            Kino.getInstance().holeBuchung(buchungsNummer);
        } catch (NoSuchElementException e) {
            //alles ok!
            thrown = true;
        } finally {
            if (!thrown){
                buchungDTO.setMessage("Diese Buchungsnummer ist bereits vergeben!");
                return ResponseEntity.badRequest().body(buchungDTO);
            }
        }
        Vorfuehrung vorfuehrung;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(buchungDTO.getVorfuehrungDTO().getVorfuehrungNummer());
        } catch (NoSuchElementException e) {
            buchungDTO.setMessage("Vorfuehrungsnummer unbekannt");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        Buchung buchung;
        try {
            buchung = Buchung.createFresh(
                    buchungsNummer,
                    vorfuehrung
            );
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Buchung konnte nicht persisitert werden");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        Resevierung resevierung;
        try {
            resevierung = Kino.getInstance().holeReservierung(buchungDTO.getReservierungDTO().getName());
        } catch (NoSuchElementException e) {
            buchungDTO.setMessage("Die angegebene Reservierung konnte nicht gefunden werden");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        if (resevierung.getIstBereitsEingeloest()){
            buchungDTO.setMessage("Die Reseriverung ist bereits Eingelöst. Es kann nicht mehr gebucht werden");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        if (vorfuehrung.getBereitsVorbei()){
            buchungDTO.setMessage("Die Vorfuehrung ist vorbei. Eine Buchung ist nicht mehr moeglich");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        //hole bestehende Buchungen und die dazugehörigen BuchungseinheitsDTOs
        ResponseEntity<Set<BuchungDTO>> buchungsDTOs = new HoleBuchungService().holeBuchungen(vorfuehrung.getVorfuehrungsNummer());
        if (!buchungsDTOs.getStatusCode().equals(HttpStatus.ACCEPTED)){
            buchungDTO.setMessage("Die bestehenden Buchungen zur vorfuehrung konnten nicht geholt werden"+buchungsDTOs.getBody().toString());
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        //hole die Vorfuehrung
        ResponseEntity<VorfuehrungDTO> vorfuehrungDTOResponse = new HoleVorfuehrungService().holeVorfuehrung(vorfuehrung.getVorfuehrungsNummer());
        if (vorfuehrungDTOResponse.getStatusCode() != HttpStatus.ACCEPTED){
            buchungDTO.setMessage("Die zugehoerige Vorfuehrung konnte nicht gefunden werden: "+vorfuehrungDTOResponse.getBody().getMessage());
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        VorfuehrungDTO vorfuehrungDTO = vorfuehrungDTOResponse.getBody();
        buchungDTO.setVorfuehrungDTO(vorfuehrungDTO);
        buchungDTO.setBuchungsNummer(buchungsNummer);

        Set<BuchungseinheitDTO> buchungseinheitDTOs = new HashSet<>();
        buchungsDTOs.getBody().forEach(
                buchungDTO1 -> buchungDTO1.
                        getBuchungseinheitDTOs().forEach(
                        buchungseinheitDTO1 ->
                                buchungseinheitDTOs.add(buchungseinheitDTO1)));
        //there have to be two for-loops, because we have to check, if all seats are free before persisting the buchung-object in the DB
        for (BuchungseinheitDTO buchungseinheitDTO : buchungDTO.getBuchungseinheitDTOs()) {
            int reihenNummer = buchungseinheitDTO.getSitzDTO().getReiheDTO().getReihenNummer();
            int sitzNummer = buchungseinheitDTO.getSitzDTO().getSitzNummer();

            if (platzBelegt(reihenNummer, sitzNummer, buchungseinheitDTOs)) {
                buchungDTO.setMessage("Der Platz " + sitzNummer + " in der Reihe " + reihenNummer + " ist bereits belegt!!!");
                return ResponseEntity.badRequest().body(buchungDTO);
            }


            for (ReiheDTO reiheDTO : vorfuehrungDTO.getSaalDTO().getReihen()){
                if (reiheDTO.getReihenNummer() == reihenNummer){
                    try {
                        if (!KategorieDTO.getDTOForKategorie(resevierung.getKategorie()).equals(reiheDTO.getKategorieDTO())){
                            buchungDTO.setMessage("Du versuchst gerade einen Platz in einer Kategorie zu buchen, in der du nicht reserviert hast. Du Schlingel!");
                            return ResponseEntity.badRequest().body(buchungDTO);
                        }
                    } catch (PersistenceException e) {
                        buchungDTO.setMessage("Der Reservierung ist nicht valide eine Kategorie zugeordnet");
                        return ResponseEntity.badRequest().body(buchungDTO);
                    }
                }
            }


        }
        for (BuchungseinheitDTO buchungseinheitDTO : buchungDTO.getBuchungseinheitDTOs()) {
            int reihenNummer = buchungseinheitDTO.getSitzDTO().getReiheDTO().getReihenNummer();
            int sitzNummer = buchungseinheitDTO.getSitzDTO().getSitzNummer();

            Sitz sitz = null;
            //this is necessary, because the generated code does not treat arrays consistently for some reason
            try {
                List<Reihe> reihen = vorfuehrung.getSaal().getReihen();
                for (Reihe reihe : reihen){
                    if (reihe.getReihenNummer() == reihenNummer) {
                        for (Sitz sitz1 : reihe.getSitze()){
                            if (sitz1.getSitzNummer() == sitzNummer)
                                sitz = sitz1;
                        }
                    }

                }
                if (sitz == null) throw new IndexOutOfBoundsException();
                    //sitz = vorfuehrung.getSaal().getReihen().get(reihenNummer-1).getSitze().get(sitzNummer-1);
            } catch (PersistenceException e) {
                buchungDTO.setMessage("Der/die gewuenschte Saal/Reihe/Sitz konnten nicht gefunden werden");
                return ResponseEntity.badRequest().body(buchungDTO);
            } catch (IndexOutOfBoundsException e){
                buchungDTO.setMessage("Die/der gewuenschte Reihe/Sitz existiert nicht");
                return ResponseEntity.badRequest().body(buchungDTO);
            }
            BuchungsEinheit buchungsEinheit;
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
    private boolean platzBelegt(int reiheNr, int sitzNr, Set<BuchungseinheitDTO> buchungseinheitDTOs){
        for (BuchungseinheitDTO buchungseinheitDTO : buchungseinheitDTOs){
            if (buchungseinheitDTO.getSitzDTO().getReiheDTO().getReihenNummer() == (reiheNr)
                 && buchungseinheitDTO.getSitzDTO().getSitzNummer() == (sitzNr)) return true;
        }
        return false;
    }
}
