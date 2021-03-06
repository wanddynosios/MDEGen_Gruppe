package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SaalDTO extends DTO{
    @NotNull
    private Integer saalNummer;
    private List<ReiheDTO> reihen;

    public SaalDTO(@NotNull Integer saalNummer) {
        this.saalNummer = saalNummer;
    }

    @Override
    public String toString() {
        return "SaalDTO{" +
                "saalNummer=" + saalNummer +
                ", reihen=" + reihen +
                '}';
    }
}
