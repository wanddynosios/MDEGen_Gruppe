package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SitzMitBelegungDTO extends SitzDTO{
    private boolean belegt = false;
}
