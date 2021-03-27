package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.*;
import exceptions.ConstraintViolation;
import generated.kino.*;
import generated.kino.proxies.VorfuehrungProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class HoleVorfuehrungService {
    @GetMapping("/vorfuehrung/all")
    public ResponseEntity<Set<VorfuehrungDTO>> getVorfuehrungen() {
        Map<Integer, VorfuehrungProxy> vorfuehrungCache = Kino.getInstance().getVorfuehrungCache();
        Set<Vorfuehrung> vorfuehrungen = new HashSet<>();
        vorfuehrungCache.forEach((integer, vorfuehrungProxy) -> vorfuehrungen.add(vorfuehrungProxy.getTheObject()));
        Set<VorfuehrungDTO> vorfuehrungDTOs = new HashSet<>();
        for (Vorfuehrung vorfuehrung : vorfuehrungen) {
            VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
            Set<Resevierung> reservierungen = null;
            try {
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
            reservierungen = vorfuehrung.getReservierungen();
            } catch (PersistenceException e){
                vorfuehrungDTO.setMessage("Persistenzfehler in einer der Vorfuerhrungen");
                vorfuehrungDTOs.add(vorfuehrungDTO);
                return ResponseEntity.badRequest().body(vorfuehrungDTOs);
            }
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
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        Vorfuehrung vorfuehrung;
        try {
            vorfuehrung = Kino.getInstance().holeVorfuehrung(vorfuehrungsNummer);
        } catch (NoSuchElementException e) {
            vorfuehrungDTO.setMessage("Vorfuehrungsnummer unbekannt");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        vorfuehrungDTO.setVorfuehrungNummer(vorfuehrung.getVorfuehrungsNummer());
        try {
            vorfuehrungDTO.setFilmDTO(new FilmDTO(vorfuehrung.getFilm().getFilmName()));
        } catch (PersistenceException e) {
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
        } catch (PersistenceException constraintViolation) {
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
                    List<ReiheDTO> reiheDTOs = saalDTO.getReihen();
                    for (ReiheDTO reiheDTO : reiheDTOs){
                        if (reiheDTO.getReihenNummer() == reiheNummer){
                            for (SitzDTO sitzDTO : reiheDTO.getSitze()){
                                if (sitzDTO.getSitzNummer() == sitzNummer)
                                    sitzDTO.setBelegt(true);
                            }
                        }

                    }
                //saalDTO.getReihen().get(reiheNummer - 1 ).getSitze().get(sitzNummer -1).setBelegt(true);
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
            vorfuehrungDTO.setMessage("Der Vorfuehrung sind keine validen Reservierungen zugeordnet");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        Set<ReservierungDTO> reservierungDTOs = new HashSet<>();
        for (Resevierung resevierung : reservierungen) {
            reservierungDTOs.add(new ReservierungDTO(resevierung.getName()));
        }
        vorfuehrungDTO.setReservierungDTOs(reservierungDTOs);
        try {
            vorfuehrungDTO.setErwarteterUmsatz(Kino.getInstance().erhebeErwartetenUmsatz(vorfuehrung));
        } catch (PersistenceException e) {
            vorfuehrungDTO.setMessage("Persistenzfehler beim erheben des erwarteten Umsatzes");
            return ResponseEntity.badRequest().body(vorfuehrungDTO);
        }
        vorfuehrungDTO.setVorfuehrungVorbei(vorfuehrung.getBereitsVorbei());
        return ResponseEntity.accepted().body(vorfuehrungDTO);
    }
}
