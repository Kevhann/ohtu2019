package ohtu.verkkokauppa;

import ohtu.interfaces.PankkiInterface;
import ohtu.interfaces.KirjanpitoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements PankkiInterface {

    private KirjanpitoInterface kirjanpito;

    @Autowired
    public Pankki(KirjanpitoInterface kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille + " viite " + viitenumero
                + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
