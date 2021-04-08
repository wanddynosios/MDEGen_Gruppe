# Einrichtung
* IntelliJ Idea empfohlen
1. Importiere MDEGen als Modul in das Root-Projekt (File -> Project Structure -> Modules -> ("+") -> Import -> MDEGen auswählen -> ok, ok, ...)
2. Setze den Output-Path (im neuen Modul: Path -> Use Module Compile Output Path -> z.B. {root}/out)
3. Ersetze im build.gradle die Dependency: compile files('C:\\Path\\to\\out\\production\\MDEGen')
4. Project build (grüner Hammer) evtl. mehrmals ausführen
5. Nach Änderung in MDEGen neuer build
6. Starte einen mysql-server (XAMPP?) und importiere backup_fresh_db.sql

# Gehört dazu:
MDE: github.com/wanddynosios/MDEGruppe
Frontend: github.com/wanddynosios/cinemaUI (Forked from lowbe/cinemaUI)
