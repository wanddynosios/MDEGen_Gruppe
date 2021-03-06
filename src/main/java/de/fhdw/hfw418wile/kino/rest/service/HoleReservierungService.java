package de.fhdw.hfw418wile.kino.rest.service;

import db.executer.PersistenceException;
import de.fhdw.hfw418wile.kino.rest.dto.KategorieDTO;
import de.fhdw.hfw418wile.kino.rest.dto.ReservierungDTO;
import de.fhdw.hfw418wile.kino.rest.dto.VorfuehrungDTO;
import generated.kino.Kino;
import generated.kino.Resevierung;
import generated.kino.proxies.ResevierungProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class HoleReservierungService {
    @GetMapping("/reservierung/all/{vorfuehrungsNummer}")
    public ResponseEntity<Set<ReservierungDTO>> holeReservierungen(@PathVariable Integer vorfuehrungsNummer){
        Map<Integer, ResevierungProxy> reservierungCache = Kino.getInstance().getResevierungCache();
        Map<Integer, Integer> reservierungsIdsToVorfuehrNummer = new HashMap<>();
        AtomicBoolean thrown = new AtomicBoolean(false);
        reservierungCache.forEach((reservierungsId, resevierungProxy) ->
        {
            try {
                reservierungsIdsToVorfuehrNummer.put(
                        reservierungsId,
                        resevierungProxy.getVorfuehrung().getVorfuehrungsNummer());
            } catch (PersistenceException e) {

                thrown.set(true);
            }
        });
        if (thrown.get()){
            ReservierungDTO reservierungDTO = new ReservierungDTO();
            reservierungDTO.setMessage("Eine Reservierung hat keine zugeordnete Vorfuehrung");
            Set<ReservierungDTO> reservierungDTOs = new HashSet<ReservierungDTO>();
            reservierungDTOs.add(reservierungDTO);
            return ResponseEntity.badRequest().body(reservierungDTOs);
        }
        Set<Integer> reservierungsIds = getKeysByValue(reservierungsIdsToVorfuehrNummer, vorfuehrungsNummer);
        Set<ReservierungDTO> reservierungDTOs = new HashSet<>();
        for (Integer reservierungsId : reservierungsIds){
            ReservierungDTO reservierungDTO = new ReservierungDTO();
            Resevierung resevierung = null;
            try {
                resevierung = Kino.getInstance().getResevierung(reservierungsId);
                reservierungDTO.setKategorieDTO(KategorieDTO.getDTOForKategorie(resevierung.getKategorie()));
            } catch (Exception e) {
                reservierungDTO.setMessage("Eine Reservierung hat keine zugeordnete (valide) Kategorie");
            }
            reservierungDTO.setName(resevierung.getName());
            reservierungDTO.setAnzahlPlaetze(resevierung.getAnzahlPlaetze());
            reservierungDTO.setIstBereitsEingeloest(resevierung.getIstBereitsEingeloest());
            reservierungDTOs.add(reservierungDTO);
        }
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        vorfuehrungDTO.setVorfuehrungNummer(vorfuehrungsNummer);
        for (ReservierungDTO reservierungDTO : reservierungDTOs){
            reservierungDTO.setVorfuehrungDTO(vorfuehrungDTO);
        }
        return ResponseEntity.accepted().body(reservierungDTOs);
    }


    /*
    credits: https://stackoverflow.com/a/2904266
     */
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }
}
