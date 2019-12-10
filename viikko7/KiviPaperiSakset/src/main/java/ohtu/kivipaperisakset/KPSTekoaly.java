package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPS {

    private TekoalyInterface tekoaly;

    public KPSTekoaly(TekoalyInterface tekoaly) {
        this.tekoaly = tekoaly;
    }

    @Override
    protected void getToinenSiirto() {
        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
    }
}