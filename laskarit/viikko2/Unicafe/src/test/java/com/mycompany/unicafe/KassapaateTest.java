package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(0);
    }

    @Test
    public void kassapaatteenRahamaaraAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullisiaLounaitaMyytyAlussaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaitaLounaitaMyytyAlussaOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKateisellaKasvattaaRahamaaraaOikein() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiKateisellaKasvattaaRahamaaraaOikein() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiKateisellaKasvattaaMyytyjaLounaitaOikein() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKateisellaKasvattaaMyytyjaLounaitaOikein() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiPalauttaaVaihtorahanOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }

    @Test
    public void syoMaukkaastiPalauttaaVaihtorahanOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }

    @Test
    public void syoEdullisestiEiMuutaRahamaaraaKunKateinenEiRiita() {
        kassa.syoEdullisesti(239);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiEiMuutaRahamaaraaKunKateinenEiRiita() {
        kassa.syoMaukkaasti(399);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiEiMuutaMyytyjaLounaitaKunKateinenEiRiita() {
        kassa.syoEdullisesti(239);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiEiMuutaMyytyjaLounaitaKunKateinenEiRiita() {
        kassa.syoMaukkaasti(399);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiPalauttaaVaihtorahanOikeinKunKateinenEiRiita() {
        assertEquals(239, kassa.syoEdullisesti(239));
    }

    @Test
    public void syoMaukkaastiPalauttaaVaihtorahanOikeinKunKateinenEiRiita() {
        assertEquals(399, kassa.syoMaukkaasti(399));
    }

    @Test
    public void syoEdullisestiKortillaEiMuutaRahamaaraa() {
        kortti.lataaRahaa(240);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiKortillaEiMuutaRahamaaraa() {
        kortti.lataaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiKortillaKasvattaaMyytyjaLounaitaOikein() {
        kortti.lataaRahaa(240);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaKasvattaaMyytyjaLounaitaOikein() {
        kortti.lataaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKortillaVahentaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaVahentaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaPalauttaaTrueKunSaldoRiittaa() {
        kortti.lataaRahaa(500);
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void syoMaukkaastiKortillaPalauttaaTrueKunSaldoRiittaa() {
        kortti.lataaRahaa(500);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void syoEdullisestiEiMuutaKortinSaldoaKunSummaEiRiita() {
        kortti.lataaRahaa(239);
        kassa.syoEdullisesti(kortti);
        assertEquals(239, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiEiMuutaKortinSaldoaKunSummaEiRiita() {
        kortti.lataaRahaa(399);
        kassa.syoMaukkaasti(kortti);
        assertEquals(399, kortti.saldo());
    }

    @Test
    public void syoEdullisestiEiMuutaMyytyjaLounaitaKunKortinSaldoEiRiita() {
        kortti.lataaRahaa(239);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiEiMuutaMyytyjaLounaitaKunKortinSaldoEiRiita() {
        kortti.lataaRahaa(399);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiPalauttaaFalseKunKortinSaldoEiRiita() {
        kortti.lataaRahaa(239);
        assertFalse(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void syoMaukkaastiPalauttaaFalseKunKortinSaldoEiRiita() {
        kortti.lataaRahaa(399);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void lataaRahaaKortilleMuuttaaKortinSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(500, kortti.saldo());
    }

    @Test
    public void lataaRahaaKortilleMuuttaaKassanRahamaaraaOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }


    @Test
    public void lataaRahaaKortilleEiMuutaKortinSaldoaKunSummaOnNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(0, kortti.saldo());
    }

    @Test
    public void lataaRahaaKortilleEiMuutaKassanRahamaaraaKunSummaOnNegatiivinen() {
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
