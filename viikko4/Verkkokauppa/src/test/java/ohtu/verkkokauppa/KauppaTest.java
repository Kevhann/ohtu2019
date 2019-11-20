package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
  Pankki pankki;
  Viitegeneraattori viite;
  Varasto varasto;
  Kauppa k;

  @Before
  public void setUp() {
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
    k = new Kauppa(varasto, pankki, viite);

    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    when(varasto.saldo(2)).thenReturn(10);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "ES", 6));

    when(varasto.saldo(3)).thenReturn(0);

  }

  @Test
  public void metodiAloitaAsiointiNollaaEdellisenOstoksenTiedot() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.aloitaAsiointi();
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 6);

  }

  @Test
  public void poistettuTuoteEiVaikutaHintaan() {
    k.aloitaAsiointi();

    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.poistaKorista(1);

    k.tilimaksu("pekka", "12345");

    // String nimi, int viitenumero, String tililta, String tilille, int summa) {
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 6);

  }

  @Test
  public void metodiAloitaAlustaPyytääUudenViitenumeron() {
    k.aloitaAsiointi();
    verify(viite, times(0)).uusi();
    k.tilimaksu("pekka", "12345");
    verify(viite, times(1)).uusi();
    k.tilimaksu("pekka", "12345");
    verify(viite, times(2)).uusi();
    k.tilimaksu("pekka", "12345");
    verify(viite, times(3)).uusi();

  }

  @Test
  public void toisenTuotteenLoputtuaOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(3); // ostetaan tuotetta numero 2 eli jumalten juomaa
    k.tilimaksu("pekka", "12345");

    // String nimi, int viitenumero, String tililta, String tilille, int summa) {
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void useanSamanOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(2); // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli jumalten juomaa
    k.tilimaksu("pekka", "12345");

    // String nimi, int viitenumero, String tililta, String tilille, int summa) {
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 12);
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void useanOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli jumalten juomaa
    k.tilimaksu("pekka", "12345");

    // String nimi, int viitenumero, String tililta, String tilille, int summa) {
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 11);
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // String nimi, int viitenumero, String tililta, String tilille, int summa) {
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }
}
