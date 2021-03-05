package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SitzDTO {
    private Integer sitzNummer;

    public SitzDTO(Integer sitzNummer) {
        this.sitzNummer = sitzNummer;
    }
}