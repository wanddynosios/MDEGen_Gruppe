# Einrichtung
* IntelliJ Idea empfohlen
1. Importiere MDEGen als Modul in das Root-Projekt (File -> Project Structure -> Modules -> ("+") -> Import -> MDEGen ausw�hlen -> ok, ok, ...)
2. Setze den Output-Path (im neuen Modul: Path -> Use Module Compile Output Path -> z.B. {root}/out)
3. Ersetze im build.gradle die Dependency: compile files('C:\\Path\\to\\out\\production\\MDEGen')
4. Project build (gr�ner Hammer) evtl. mehrmals ausf�hren
5. Nach �nderung in MDEGen neuer build

# Geh�rt dazu:
MDE: github.com/wanddynosios/MDEGruppe
Frontend: github.com/wanddynosios/cinemaUI (Forked from lowbe/cinemaUI)