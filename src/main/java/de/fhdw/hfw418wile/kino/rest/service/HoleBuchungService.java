package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.*;
import generated.kino.*;
import generated.kino.proxies.BuchungProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class HoleBuchungService {
    @GetMapping("/buchung/{buchungsNummer}")
    public ResponseEntity<BuchungDTO> holeBuchung(@PathVariable Integer buchungsNummer){
        Buchung buchung = null;
        BuchungDTO buchungDTO = new BuchungDTO();
        try {
            buchung = Kino.getInstance().holeBuchung(buchungsNummer);
        } catch (NoSuchElementException e) {
            buchungDTO.setMessage("Buchungsnummer unbekannt");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        buchungDTO.setBuchungsNummer(buchung.getBuchungsNummer());
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        Vorfuehrung vorfuehrung = null;
        try {
            vorfuehrung = buchung.getVorfuehrung();
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Der Buchung ist keine valide vorfuehrung zugeordnet");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        vorfuehrungDTO.setVorfuehrungNummer(vorfuehrung.getVorfuehrungsNummer());
        try {
            vorfuehrungDTO.setFilmDTO(new FilmDTO(vorfuehrung.getFilm().getFilmName()));
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Der Vorfuehrung ist kein valider Film zugeordnet");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        try {
            vorfuehrungDTO.setSaalDTO(new SaalDTO(vorfuehrung.getSaal().getSaalNummer()));
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Der Vorfuehrung ist kein valider Saal zugeordnet");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        buchungDTO.setVorfuehrungDTO(vorfuehrungDTO);
        Set<BuchungseinheitDTO> buchungseinheitenDTOs = new HashSet<>();
        try {
            for (BuchungsEinheit buchungsEinheit : buchung.getBuchungsEinheiten()){
                Sitz sitz = buchungsEinheit.getSitz();
                Reihe reihe = sitz.getReihe();
                SitzDTO sitzDTO = new SitzDTO(sitz.getSitzNummer());
                ReiheDTO reiheDTO = new ReiheDTO(reihe.getReihenNummer());
                sitzDTO.setReiheDTO(reiheDTO);

                BuchungseinheitDTO buchungseinheitDTO = new BuchungseinheitDTO(sitzDTO);
                buchungseinheitenDTOs.add(buchungseinheitDTO);
            }
        } catch (PersistenceException e) {
            buchungDTO.setMessage("Eine oder mehere Buchungseinheit(en) sind nicht valide in der DB");
            return ResponseEntity.badRequest().body(buchungDTO);
        }
        buchungDTO.setBuchungseinheitDTOs(buchungseinheitenDTOs);
        return ResponseEntity.accepted().body(buchungDTO);
    }
    @GetMapping("/buchung/all/{vorfuehrungsNummer}")
    public ResponseEntity<Set<BuchungDTO>> holeBuchungen(@PathVariable Integer vorfuehrungsNummer){
        Set<BuchungDTO> buchungDTOs = new HashSet<>();
        Map<Integer, BuchungProxy> buchungCache = Kino.getInstance().getBuchungCache();
        Set<Buchung> buchungen = new HashSet<>();
        AtomicReference<Boolean> thrown = new AtomicReference<>(false);
        buchungCache.forEach((buchungId, buchungProxy) -> {
            try {
                if (buchungProxy.getVorfuehrung().getVorfuehrungsNummer() == vorfuehrungsNummer){
                    buchungen.add(buchungProxy.getTheObject());
                }
            } catch (PersistenceException e) {
                BuchungDTO buchungDTO = new BuchungDTO();
                buchungDTO.setMessage("Eine vorfuehrung hatte keine Vorfuehrungsnummer");
                buchungDTOs.add(buchungDTO);
                thrown.set(true);
            }
        });
        if (thrown.get())
            return ResponseEntity.badRequest().body(buchungDTOs);
        for (Buchung buchung : buchungen){
            Integer buchungsNummer = buchung.getBuchungsNummer();
            ResponseEntity<BuchungDTO> responseEntity = holeBuchung(buchungsNummer);
            if (responseEntity.getStatusCode() != HttpStatus.ACCEPTED){
                BuchungDTO buchungDTO = new BuchungDTO();
                buchungDTO.setMessage("Eine Buchung konnte nicht richtig geholt werden: "+responseEntity.getBody().getMessage());
                buchungDTOs.add(buchungDTO);
                return ResponseEntity.badRequest().body(buchungDTOs);
            }
            BuchungDTO buchungDTO = responseEntity.getBody();
            buchungDTOs.add(buchungDTO);
        }
        return ResponseEntity.accepted().body(buchungDTOs);
    }
}
