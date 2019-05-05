# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovellus on Tetris-peli, jossa pelaaja asettelee erimuotoisia, putoavia palikoita ruudukossa. Pelin tavoite on kerätä
pisteitä kokoamalla palikoista täysiä vaakarivejä. Kun pelaaja on saanut koottua tietyn määrän rivejä, peli siirtyy 
seuraavalle tasolle ja palikoiden putoamisnopeus kasvaa. Mitä korkeampi pelin taso on ja mitä useamman rivin pelaaja
muodostaa kerralla, sitä hän saa pisteitä. Täydet rivit poistetaan, ja peliin otetaan uusi palikka. Kun luotu palikka
ei enää mahdu ruudukkoon, peli päättyy. 

## Käyttöliittymä

Sovelluksessa on graafinen käyttöliittymä, jossa on neljä näkymää: päävalikko, pelin asetukset, pelinäkymä ja
pistetilasto. 
  
## Toiminnallisuus

### Pelin asetukset
- pelille voi valita helpon, normaalin tai vaikean vaikeustason, jonka perusteella pelin aloitustaso valitaan.
  Mitä korkeampi pelin taso on, sitä nopeammin palikat putoavat.

### Pelin pelaaminen
- putoavaa palikkaa voi liikutella vasemmalle ja oikealle nuolinäppäimillä
- putoavaa palikkaa voi kääntää ylänuolinäppäimellä
- putoavan palikan voi pudottaa alanuolinäppäimellä

### Pelilogiikka
- täydet vaakarivit tyhjennetään, ja peliin tuodaan uusi palikka
- jos peliruudukkoon ei mahdu uusia palikoita, peli päättyy
- pelaaja kerää pisteitä muodostaessaan täyden rivin
- usean täyden rivin muodostamista kerralla saa enemmän pisteitä, ja pisteet kerrotaan pelin
 tason mukaisella kertoimella
- täysiä rivejä muodostamalla etenee seuraavalle tasolle

### Pistetulokset
- kun peli päättyy käyttäjä syöttää nimensä, ja pistetulos tallennetaan tietokantaan
- pistetilastosta näkee 10 parasta tulosta ja niiden tekijät


## Jatkokehitysideoita
- mahdollisuus keskeyttää meneillään oleva peli
- mahdollisuus rekisteröityä ja nähdä tilasto omista parhaista pistetuloksista 