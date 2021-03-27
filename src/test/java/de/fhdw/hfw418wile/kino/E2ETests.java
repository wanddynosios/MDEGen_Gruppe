package de.fhdw.hfw418wile.kino;

import de.fhdw.hfw418wile.kino.rest.dto.*;
import de.fhdw.hfw418wile.kino.rest.service.*;
import generated.kino.Kino;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class E2ETests {
    public static Long time = null;
    public static Integer saalNummer = null;
    public static String filmName = null;
    public static Integer vorfuehrungsNummer = null;

    @BeforeAll
    public static void init(){
        time = System.currentTimeMillis();
        saalNummer = time.intValue();
        filmName = "TestFilm"+time;
        Kino.getInstance();
    }

    @Test
    @Order(1)
    public void testFilm(){
        FilmDTO filmDTO = new FilmDTO(filmName);
        ResponseEntity<FilmDTO> filmDTOResponseEntity = new ErstelleFilmService().erstelleFilm(filmDTO);
        Assertions.assertEquals(filmDTOResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        ResponseEntity<FilmDTO> geholteFilmeResponseEntity = new HoleFilmService().holeFilm(filmName);
        Assertions.assertEquals(geholteFilmeResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        Assertions.assertEquals(geholteFilmeResponseEntity.getBody().getFilmName(), filmName);
    }

    @Test
    @Order(2)
    public void testSaal(){
        SaalDTO saalDTO = new SaalDTO();
        saalDTO.setSaalNummer(saalNummer);
        ReiheDTO reihe1 = new ReiheDTO();
        reihe1.setSitze(new ArrayList<SitzDTO>());
        reihe1.setReihenNummer(1);
        for (int i = 0; i < 5; i++) {
            SitzDTO sitzDTO = new SitzDTO();
            sitzDTO.setSitzNummer(i);
            reihe1.getSitze().add(sitzDTO);
        }
        reihe1.setKategorieDTO(KategorieDTO.KategorieParkett);
        ReiheDTO reihe2 = new ReiheDTO();
        reihe2.setSitze(new ArrayList<SitzDTO>());
        reihe2.setReihenNummer(2);
        for (int i = 0; i < 4; i++) {
            SitzDTO sitzDTO = new SitzDTO();
            sitzDTO.setSitzNummer(i);
            reihe2.getSitze().add(sitzDTO);
        }
        reihe2.setKategorieDTO(KategorieDTO.KategorieMitte);
        ReiheDTO reihe3 = new ReiheDTO();
        reihe3.setSitze(new ArrayList<SitzDTO>());
        reihe3.setReihenNummer(3);
        for (int i = 0; i < 3; i++) {
            SitzDTO sitzDTO = new SitzDTO();
            sitzDTO.setSitzNummer(i);
            reihe3.getSitze().add(sitzDTO);
        }
        reihe3.setKategorieDTO(KategorieDTO.KategorieLoge);
        saalDTO.setReihen(new ArrayList<>());
        saalDTO.getReihen().add(reihe1);
        saalDTO.getReihen().add(reihe2);
        saalDTO.getReihen().add(reihe3);

        ResponseEntity<SaalDTO> saalDTOResponseEntity = new ErstelleSaalService().erstelleSaal(saalDTO);
        Assertions.assertEquals(saalDTOResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);

        ResponseEntity<SaalDTO> geholterSaalDTO = new HoleSaalService().holeSaal(saalNummer);
        Assertions.assertEquals(geholterSaalDTO.getStatusCode(), HttpStatus.ACCEPTED);
        int successes = 0;
        for (ReiheDTO reiheDTO : geholterSaalDTO.getBody().getReihen()){
            for (SitzDTO sitzDTO : reiheDTO.getSitze()){
                if (sitzDTO.getSitzNummer() == 2 && reiheDTO.getReihenNummer() == 2){
                    if (reiheDTO.getKategorieDTO().equals(KategorieDTO.KategorieMitte))
                        successes++;
                }
                if (sitzDTO.getSitzNummer() == 1 && reiheDTO.getReihenNummer() == 3){
                    if (reiheDTO.getKategorieDTO().equals(KategorieDTO.KategorieLoge))
                        successes++;
                }
                if (sitzDTO.getSitzNummer() == 6 && reiheDTO.getReihenNummer() ==  1){
                    Assertions.fail("Dieser Platz existiert nicht");
                }
            }
        }
        if (successes != 2) Assertions.fail("Mindestens einer der sanity-checks schlug fehl");
    }

    @Test
    @Order(3)
    public void testVorfuehrung(){
        VorfuehrungDTO vorfuehrungDTO = new VorfuehrungDTO();
        vorfuehrungDTO.setSaalDTO(new SaalDTO(saalNummer));
        vorfuehrungDTO.setFilmDTO(new FilmDTO(filmName));
        vorfuehrungDTO.setPreisParkett(6);
        vorfuehrungDTO.setPreisMitte(9);
        vorfuehrungDTO.setPreisLoge(11);
        ResponseEntity<VorfuehrungDTO> vorfuehrungDTOResponseEntity = new ErstelleVorfuehrungService().erstelleVorfuehrung(vorfuehrungDTO);
        Assertions.assertEquals(vorfuehrungDTOResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        vorfuehrungsNummer = vorfuehrungDTOResponseEntity.getBody().getVorfuehrungNummer();System.out.println(vorfuehrungsNummer);
        ResponseEntity<VorfuehrungDTO> geholterVorfuehrungDTO = new HoleVorfuehrungService().holeVorfuehrung(vorfuehrungsNummer);
        Assertions.assertEquals(geholterVorfuehrungDTO.getStatusCode(), HttpStatus.ACCEPTED);
        Assertions.assertEquals(geholterVorfuehrungDTO.getBody().getFreiePlaetzeParkett(), 5);
        Assertions.assertEquals(geholterVorfuehrungDTO.getBody().getFreiePlaetzeMitte(), 4);
        Assertions.assertEquals(geholterVorfuehrungDTO.getBody().getFreiePlaetzeLoge(), 3);



    }


}
