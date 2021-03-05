package de.fhdw.hfw418wile.kino.rest.dto;

import generated.kino.Kategorie;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum KategorieDTO {
    KategorieParkett,
    KategorieMitte,
    KategorieLoge;

    public static KategorieDTO getDTOForKategorie(Kategorie kategorie) {
        switch (kategorie.getClass().getSimpleName()){
            case "KategorieParkett":
                return KategorieParkett;
            case "KategorieMitte" :
                return KategorieMitte;
            case "KategorieLoge":
                return KategorieLoge;
            default: throw new RuntimeException("Unbekannte Kategorie. " +
                    "Schreibweise geaendert?");
        }
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
