# Käyttöohje

Lataa tiedosto [tetris.jar](https://github.com/mhaapakangas/ot-harjoitustyo/releases/tag/viikko6)

## Ohjelman käynnistäminen
Sovellus käynnistetään komennolla

```
java -jar tetris.jar
```

## Uuden pelin aloittaminen
Menu-näkymän painikkeesta *New game* voi aloittaa uuden pelin.
Pelin tetris-palikkaa voi liikutella seuraavasti:

| Näppäin      | Tapahtuma |
| ----------- | ----------- |
| Vasen-nuoli  | Liikuta palikkaa vasemmalle |
| Oikea-nuoli  | Liikuta palikkaa oikealle |
| Ylös-nuoli  | Käännä palikkaa |
| Alas-nuoli  | Pudota palikka |

Kun pelajaa muodostaa palikoista täysiä rivejä, niistä kerääntyy pisteitä ja täydet rivit
poistetaan. Täyttämällä useampia rivejä kerralla, saa enemmän pisteitä. Pelinäkymän ylälaidassa
näkyy sen hetkinen pistetilanne ja pelin vaikeustaso. Pisteiden kertyessä peli siirtyy seuraavalle
vaikeustasolle, ja palikoiden putoamisnopeus kasvaa.

## Pelin päättyminen
Kun peli päättyy, pelaaja voi lisätä nimensä Game over -näkymän tekstilaatikkoon
ja tallentaa pistetuloksensa *Save score* painikkeesta.

## Parhaiden pisteiden katsominen
Menu-näkymän painikkeesta *High scores* pääsee parhaat pisteet -näkymään, jolta 
näkee 10 parasta pistetulosta ja ne tehneet pelaajat. *Back to Menu* painikkeesta
pääsee takaisiin menuun.
