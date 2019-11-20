
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int oletusKoko = 5; // aloitustalukon koko

    private int kasvatuskoko = 5; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm = 0; // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        joukko = new int[oletusKoko];
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        joukko = new int[kapasiteetti];

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");// heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");// heitin vaan jotain :D
        }
        joukko = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;

    }

    public void kasvataJoukkoa() {
        int[] taulukkoOld = new int[joukko.length + kasvatuskoko];

        kopioiTaulukko(joukko, taulukkoOld);
        joukko = taulukkoOld;

    }

    public boolean lisaa(int luku) {

        if (kuuluu(luku)) {
            return false;
        } else {
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == joukko.length) {
                kasvataJoukkoa();
            }
            return true;
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;

    }

    public int loytyyIndeksista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return i;
            }
        }
        return -1;

    }

    public boolean poista(int luku) {
        int kohta = loytyyIndeksista(luku);
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                joukko[j] = joukko[j + 1];
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";

        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += joukko[i] + ", ";

            }

            tuotos += joukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }
        return taulu;
    }

    public int otaIndeksilla(int indeksi) {
        if (indeksi < 0 || indeksi >= alkioidenLkm) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");// heitin vaan jotain :D

        }
        return joukko[indeksi];
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {

        for (int i = 0; i < b.mahtavuus(); i++) {
            a.lisaa(b.otaIndeksilla(i));
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();

        for (int i = 0; i < a.mahtavuus(); i++) {
            for (int j = 0; j < b.mahtavuus(); j++) {
                if (a.otaIndeksilla(i) == b.otaIndeksilla(j)) {
                    y.lisaa(b.otaIndeksilla(j));
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {

        for (int i = 0; i < b.mahtavuus(); i++) {
            int bAlkio = b.otaIndeksilla(i);
            a.poista(bAlkio);
        }
        return a;
    }

}