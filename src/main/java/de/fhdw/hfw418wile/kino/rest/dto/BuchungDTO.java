package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BuchungDTO extends DTO{
    private Integer buchungsNummer;
    private VorfuehrungDTO vorfuehrungDTO;
    private Set<BuchungseinheitDTO> buchungseinheitDTOs;
    private ReservierungDTO reservierungDTO;

    @Override
    public String toString() {
        return "BuchungDTO{" +
                "buchungsNummer=" + buchungsNummer +
                ", vorfuehrungDTO=" + vorfuehrungDTO +
                ", buchungseinheitDTOs=" + buchungseinheitDTOs +
                ", reservierungDTO=" + reservierungDTO +
                '}';
    }
}
