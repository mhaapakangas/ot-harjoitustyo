# Ot-harjoitustyö
## Tetris-peli
Tetris on geometrinen ongelmanratkaisupeli, jossa pelaaja asettelee erimuotoisia, putovia palikoita ruudukossa.
Tavoitteena on kerätä pisteitä muodostamalla palikoista täysiä rivejä.

### Dokumentaatio
[Vaatimusmäärittely](/documentation/requirements.md)

[Työaikakirjanpito](/documentation/tuntikirjanpito.md)

### Suorittaminen
Ohjelman voi suorittaa komentoriviltä komennolla:
```
mvn compile exec:java -Dexec.mainClass=tetris.views.MenuView
```

### Testaus
Ohjelman voi testit voi ajaa komennolla:
```
mvn test
```

Testikattavuusraportin voi generoida komennolla:
```
mvn test jacoco:report
```
