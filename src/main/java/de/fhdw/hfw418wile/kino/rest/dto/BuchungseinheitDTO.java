package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuchungseinheitDTO extends DTO{
    private SitzDTO sitzDTO;

    public BuchungseinheitDTO(SitzDTO sitzDTO) {
        this.sitzDTO = sitzDTO;
    }

    @Override
    public String toString() {
        return "BuchungseinheitDTO{" +
                "sitzDTO=" + sitzDTO +
                '}';
    }
}
