# Ot-harjoitustyö
## Tetris-peli
Tetris on geometrinen ongelmanratkaisupeli, jossa pelaaja asettelee erimuotoisia, putovia palikoita ruudukossa.
Tavoitteena on kerätä pisteitä muodostamalla palikoista täysiä rivejä. Pistetulokset tallennetaan, ja sovelluksesta
näkee parhaat pisteet ja ne saavuttaneet pelaajat.

## Dokumentaatio
[Käyttöohje](/documentation/kayttoohje.md)

[Vaatimusmäärittely](/documentation/requirements.md)

[Arkkitehtuurikuvaus](/documentation/arkkitehtuuri.md)

[Testausdokumentti](/documentation/testaus.md)

[Työaikakirjanpito](/documentation/tuntikirjanpito.md)

## Releaset
[Viikko 5](https://github.com/mhaapakangas/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/mhaapakangas/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/mhaapakangas/ot-harjoitustyo/releases/tag/viikko7)

## Komentorivitoiminnot
### Ohjelman suorittaminen
Ohjelman voi suorittaa komentoriviltä komennolla:
```
mvn compile exec:java -Dexec.mainClass=tetris.views.Tetris
```

### Jar-tiedoston luominen
Ohjelmasta voi luoda jar-tiedoston target-hakemistoon komennolla: 
```
mvn package
```

Luodun ohjelman voi suorittaa komennolla:
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

### JavaDoc

JavaDocin voi generoida komennolla:
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedoston _target/site/apidocs/index.html_.

### Checkstyle
Tiedoston [checkstyle.xml](/tetris/checkstyle.xml) määrittelemät kooditarkistukset voi suorittaa komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```
Tarkistusraporttia voi tarkastella avaamalla selaimella tiedoston _target/site/checkstyle.html_.
