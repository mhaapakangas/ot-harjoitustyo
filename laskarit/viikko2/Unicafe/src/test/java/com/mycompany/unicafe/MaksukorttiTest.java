package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertNotNull(kortti);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(127);
        assertEquals(137, kortti.saldo());
    }

    @Test
    public void otaRahaaVahentaaSaldoaOikein() {
        kortti.otaRahaa(9);
        assertEquals(1, kortti.saldo());
    }

    @Test
    public void otaRahaaVahentaaSaldoaOikeinKunRahaaTasmalleenOikeaMaara() {
        kortti.otaRahaa(10);
        assertEquals(0, kortti.saldo());
    }

    @Test
    public void otaRahaaEiMuutaSaldoaKunRahaaLiianVahan() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void otaRahaaPalauttaaTrueKunKortilleJaaRahaa() {
        assertTrue(kortti.otaRahaa(9));
    }

    @Test
    public void otaRahaaPalauttaaTrueKunRahaaTasmalleenOikeaMaara() {
        assertTrue(kortti.otaRahaa(10));
    }

    @Test
    public void otaRahaaPalauttaaFalseKunRahaaLiianVahan() {
        assertFalse(kortti.otaRahaa(11));
    }

    @Test
    public void toStringPalauttaaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void toStringPalauttaaOikeinKunSenttejaAlle10(){
        kortti = new Maksukortti(105);
        assertEquals("saldo: 1.05", kortti.toString());
    }
}
