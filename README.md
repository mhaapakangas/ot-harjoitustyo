# Ot-harjoitustyö
## Tetris-peli
Tetris on geometrinen ongelmanratkaisupeli, jossa pelaaja asettelee erimuotoisia, putovia palikoita ruudukossa.
Tavoitteena on kerätä pisteitä muodostamalla palikoista täysiä rivejä.

### Dokumentaatio
[Vaatimusmäärittely](/documentation/requirements.md)

[Arkkitehtuurikuvaus](/documentation/arkkitehtuuri.md)

[Työaikakirjanpito](/documentation/tuntikirjanpito.md)

### Ohjelman suorittaminen
Ohjelman voi suorittaa komentoriviltä komennolla:
```
mvn compile exec:java -Dexec.mainClass=tetris.views.MenuView
```

### Jar-tiedoston luominen
Ohjelmasta voi luoda jar-tiedoston target-hakemistoon komennolla: 
```
mvn package
```

Ohjelman voi suorittaa komennolla:
```
java -jar target/Tetris-1.0-SNAPSHOT.jar
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
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston _target/site/jacoco/index.html_.


### Checkstyle
Tiedoston [checkstyle.xml](/tetris/checkstyle.xml) määrittelemät kooditarkistukset voi suorittaa komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```
Tarkistusraporttia voi tarkastella avaamalla selaimella tiedoston _target/site/checkstyle.html_.
