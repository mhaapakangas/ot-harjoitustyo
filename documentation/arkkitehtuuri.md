# Arkkitehtuurikuvaus
## Rakenne
Sovelluksen rakenne pakkauskaaviona:
![Arkkitehtuurikuvaus](images/PackageDiagram.jpg)

## Toiminnallisuus

### Palikan siirtäminen vasemmalle

![LiikutaVasemmalle](images/MoveLeftDiagram.png)

Kun käyttäjä painaa pelinäkymässä vasenta nuolinäppäintä, GameScene välittää pyynnön
GameService-luokalle. GameService pyytää GridService-luokalta pelitilannetta kuvaavan
taulukon, ja yrittää siirtää palikkaa vasemmalle. Shape-luokka tarkistaa, voiko palikka
liikkua haluttuun suuntaan. Jos liikkuminen onnistuu, palikan sijainti päivitetään. 
