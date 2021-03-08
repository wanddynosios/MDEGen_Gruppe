package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReiheDTO extends DTO{
    private Integer reihenNummer;
    private KategorieDTO kategorieDTO;
    private List<SitzDTO> sitze;

    public ReiheDTO(Integer reihenNummer, KategorieDTO kategorieDTO, List<SitzDTO> sitze) {
        this.reihenNummer = reihenNummer;
        this.kategorieDTO = kategorieDTO;
        this.sitze = sitze;
    }

    public ReiheDTO(Integer reihenNummer) {
        this.reihenNummer = reihenNummer;
    }

    @Override
    public String toString() {
        return "ReiheDTO{" +
                "reihenNummer=" + reihenNummer +
                ", kategorieDTO=" + kategorieDTO +
                ", sitze=" + sitze +
                '}';
    }
}
