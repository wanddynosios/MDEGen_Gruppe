package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SaalDTO {
    private Integer saalNummer;
    private List<ReiheDTO> reihen;
}
