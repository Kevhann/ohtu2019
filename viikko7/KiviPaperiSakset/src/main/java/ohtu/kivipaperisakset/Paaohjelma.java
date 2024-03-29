package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PeliTehdas tehdas = new PeliTehdas();

        while (true) {
            System.out.println("\nValitse pelataanko" + "\n (a) ihmistä vastaan " + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan" + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                System.out
                        .println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KPS kaksinpeli = tehdas.kaksinPeli();
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                System.out
                        .println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KPS helppoPeli = tehdas.helppoPeli();
                helppoPeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                System.out
                        .println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KPS vaikeaPeli = tehdas.vaikeaPeli();
                vaikeaPeli.pelaa();
            } else {
                break;
            }

        }

    }
}
