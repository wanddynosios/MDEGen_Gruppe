package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.*;
import exceptions.ConstraintViolation;
import generated.kino.Kino;
import generated.kino.NoSuchElementException;
import generated.kino.Resevierung;
import generated.kino.Vorfuehrung;
import generated.kino.proxies.VorfuehrungProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class HoleVorfuehrungService {
    @GetMapping("/vorfuehrung/all")
    public ResponseEntity<Set<VorfuehrungDTO>> getVorfuehrungen() throws PersistenceException {
        Map<Integer, VorfuehrungProxy> vorfuehrungCache = Kino.getInstance().getVorfuehrungCache();
        Set<Vorfuehrung> vorfuehrungen = new HashSet<>();
        vorfuehrungCache.forEach((integer, vorfuehrungProxy) -> vorfuehrungen.add(vorfuehrungProxy.getTheObject()));
        Set<VorfuehrungDTO> vorfuehrungDTOs = new HashSet<>();
        for (Vorfuehrung vorfuehrung : vorfuehrungen) {
            VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
            vorfuehrungDTO.setVorfuehrungNummer(vorfuehrung.getVorfuehrungsNummer());
            vorfuehrungDTO.setFilmDTO(new FilmDTO(vorfuehrung.getFilm().getFilmName()));
            vorfuehrungDTO.setFreiePlaetzeParkett(vorfuehrung.getFreiePlaetzeParkett());
            vorfuehrungDTO.setFreiePlaetzeMitte(vorfuehrung.getFreiePlaetzeMitte());
            vorfuehrungDTO.setFreiePlaetzeLoge(vorfuehrung.getFreiePlaetzeLoge());
            vorfuehrungDTO.setPreisParkett(vorfuehrung.getPreisParkett());
            vorfuehrungDTO.setPreisMitte(vorfuehrung.getPreisMitte());
            vorfuehrungDTO.setPreisLoge(vorfuehrung.getPreisLoge());
            vorfuehrungDTO.setVorfuehrungNummer(vorfuehrung.getVorfuehrungsNummer());
            vorfuehrungDTO.setSaalDTO(new SaalDTO(vorfuehrung.getSaal().getSaalNummer()));
            Set<Resevierung> reservierungen = vorfuehrung.getReservierungen();
            Set<ReservierungDTO> reservierungDTOs = new HashSet<>();
            for (Resevierung resevierung : reservierungen) {
                reservierungDTOs.add(new ReservierungDTO(resevierung.getName()));
            }
            vorfuehrungDTO.setReservierungDTOs(reservierungDTOs);
            vorfuehrungDTOs.add(vorfuehrungDTO);
        }
        return ResponseEntity.accepted().body(vorfuehrungDTOs);
    }

    @GetMapping("/vorfuehrung/{vorfuehrungsNummer}")
    public ResponseEntity<VorfuehrungDTO> holeVorfuehrung(@PathVariable Integer vorfuehrungsNummer) {
        Vorfuehrung vorfuehrung;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(vorfuehrungsNummer);
        } catch (NoSuchElementException e) {
            VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
            vorfuehrungDTO.setMessage("Vorfuehrungsnummer unbekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        vorfuehrungDTO.setVorfuehrungNummer(vorfuehrung.getVorfuehrungsNummer());
        try {
            vorfuehrungDTO.setFilmDTO(new FilmDTO(vorfuehrung.getFilm().getFilmName()));
        } catch (PersistenceException e) {
            vorfuehrungDTO = new VorfuehrungDTO();
            vorfuehrungDTO.setMessage("Der Vorfuehrung ist kein valider Film zugeordnet");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        vorfuehrungDTO.setFreiePlaetzeParkett(vorfuehrung.getFreiePlaetzeParkett());
        vorfuehrungDTO.setFreiePlaetzeMitte(vorfuehrung.getFreiePlaetzeMitte());
        vorfuehrungDTO.setFreiePlaetzeLoge(vorfuehrung.getFreiePlaetzeLoge());
        vorfuehrungDTO.setPreisParkett(vorfuehrung.getPreisParkett());
        vorfuehrungDTO.setPreisMitte(vorfuehrung.getPreisMitte());
        vorfuehrungDTO.setPreisLoge(vorfuehrung.getPreisLoge());
        vorfuehrungDTO.setVorfuehrungNummer(vorfuehrung.getVorfuehrungsNummer());
        SaalDTO saalDTO = null;
        try {
            saalDTO = new HoleSaalService().holeSaal(vorfuehrung.getSaal().getSaalNummer()).getBody();
        } catch (ConstraintViolation | PersistenceException constraintViolation) {
            vorfuehrungDTO.setMessage("Fehler im Saal: " + saalDTO.getMessage());
        }
        ResponseEntity<Set<BuchungDTO>> buchungDTOs = new HoleBuchungService().holeBuchungen(vorfuehrungsNummer);
        if (!buchungDTOs.getStatusCode().equals(HttpStatus.ACCEPTED)){
            vorfuehrungDTO.setMessage("Fehler beim holen der zugehörigen Buchungen: "+buchungDTOs.getBody());
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        for (BuchungDTO buchungDTO : buchungDTOs.getBody()){
            for (BuchungseinheitDTO buchungseinheitDTO : buchungDTO.getBuchungseinheitDTOs()){
                int reiheNummer = buchungseinheitDTO.getSitzDTO().getReiheDTO().getReihenNummer();
                int sitzNummer =buchungseinheitDTO.getSitzDTO().getSitzNummer();
                try {
                saalDTO.getReihen().get(reiheNummer - 1 ).getSitze().get(sitzNummer -1).setBelegt(true);
                } catch (IndexOutOfBoundsException e){
                    vorfuehrungDTO.setMessage("Die/der gewuenschte Reihe ("+reiheNummer+")/Sitz ("+sitzNummer+") existiert nicht und konnte nicht gebucht werden");
                    return ResponseEntity.badRequest().body(vorfuehrungDTO);
                }
            }
        }
            vorfuehrungDTO.setSaalDTO(saalDTO);
        Set<Resevierung> reservierungen;
        try {
            reservierungen = vorfuehrung.getReservierungen();
        } catch (PersistenceException e) {
            vorfuehrungDTO = new VorfuehrungDTO();
            vorfuehrungDTO.setMessage("Der Vorfuehrung sind keine validen Reservierungen zugeordnet");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        Set<ReservierungDTO> reservierungDTOs = new HashSet<>();
        for (Resevierung resevierung : reservierungen) {
            reservierungDTOs.add(new ReservierungDTO(resevierung.getName()));
        }
        vorfuehrungDTO.setReservierungDTOs(reservierungDTOs);
        return ResponseEntity.accepted().body(vorfuehrungDTO);
    }
}
