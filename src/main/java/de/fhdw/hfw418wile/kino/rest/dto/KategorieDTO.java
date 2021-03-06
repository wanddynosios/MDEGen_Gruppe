package de.fhdw.hfw418wile.kino.rest.dto;

import db.executer.PersistenceException;
import generated.kino.Kategorie;
import generated.kino.KategorieLoge;
import generated.kino.KategorieMitte;
import generated.kino.KategorieParkett;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;


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

    @SneakyThrows //Singleton.getInstance() sollte in der Regel keinen Fehler werfen
    public static Kategorie getKategroieForDTO(KategorieDTO kategorieDTO) {
        switch (kategorieDTO){
            case KategorieParkett:
                return generated.kino.KategorieParkett.getInstance();
            case KategorieMitte:
                return generated.kino.KategorieMitte.getInstance();
            case KategorieLoge:
                return generated.kino.KategorieLoge.getInstance();
            default: throw new RuntimeException("Unbekannte Kategorie. " +
                    "Schreibweise geaendert?");
        }
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
