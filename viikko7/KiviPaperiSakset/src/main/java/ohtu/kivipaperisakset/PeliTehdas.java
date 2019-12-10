package ohtu.kivipaperisakset;

public class PeliTehdas {

  public KPS kaksinPeli() {
    return new KPSPelaajaVsPelaaja();
  }

  public KPS helppoPeli() {
    return new KPSTekoaly(new Tekoaly());
  }

  public KPS vaikeaPeli() {
    return new KPSTekoaly(new TekoalyParannettu());
  }
}