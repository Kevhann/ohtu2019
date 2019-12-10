package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    protected void getToinenSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = scanner.nextLine();

    }

}