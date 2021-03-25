package de.fhdw.hfw418wile.kino.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VorfuehrungDTO extends DTO{
    private Integer vorfuehrungNummer;
    private Integer preisParkett;
    private Integer preisMitte;
    private Integer preisLoge;
    private Integer freiePlaetzeParkett;
    private Integer freiePlaetzeMitte;
    private Integer freiePlaetzeLoge;
    private Set<ReservierungDTO> reservierungDTOs;
    private FilmDTO filmDTO;
    private SaalDTO saalDTO;
    //TODO vorfuehrung ist vorbei???


    @Override
    public String toString() {
        return "VorfuehrungDTO{" +
                "vorfuehrungNummer=" + vorfuehrungNummer +
                ", preisParkett=" + preisParkett +
                ", preisMitte=" + preisMitte +
                ", preisLoge=" + preisLoge +
                ", freiePlaetzeParkett=" + freiePlaetzeParkett +
                ", freiePlaetzeMitte=" + freiePlaetzeMitte +
                ", freiePlaetzeLoge=" + freiePlaetzeLoge +
                ", reservierungDTOs=" + reservierungDTOs +
                ", filmDTO=" + filmDTO +
                ", saalDTO=" + saalDTO +
                '}';
    }
}
