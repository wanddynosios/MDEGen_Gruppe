package de.fhdw.hfw418wile.kino;

import generated.kino.Kino;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KinoApplication {

    public static void main(String[] args) {
        Kino.getInstance();
        SpringApplication.run(KinoApplication.class, args);
    }

}
