package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservierungDTO {
    private String name;
    private VorfuehrungDTO vorfuehrungDTO;
    private KategorieDTO kategorieDTO;
    private Integer anzahlPlaetze;

    @Override
    public String toString() {
        return "ReservierungDTO{" +
                "name='" + name + '\'' +
                ", vorfuehrungDTO=" + vorfuehrungDTO +
                ", kategorieDTO=" + kategorieDTO +
                ", anzahlPlaetze=" + anzahlPlaetze +
                '}';
    }
}
