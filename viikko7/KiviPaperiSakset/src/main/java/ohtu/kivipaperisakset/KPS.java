package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {
  private Tuomari tuomari = new Tuomari();

  private static final Scanner scanner = new Scanner(System.in);
  protected String ekanSiirto;
  protected String tokanSiirto;

  public void pelaa() {
    aloitus();
    while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
      ensimmainenSiirto();
      getToinenSiirto();
    }
    pelinLoppu();
  }

  private void aloitus() {
    System.out.print("Ensimmäisen pelaajan siirto: ");
    ekanSiirto = scanner.nextLine();
    getToinenSiirto();

  }

  private void ensimmainenSiirto() {
    tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
    System.out.println(tuomari);
    System.out.println();

    System.out.print("Ensimmäisen pelaajan siirto: ");
    ekanSiirto = scanner.nextLine();
  }

  private void pelinLoppu() {
    System.out.println();
    System.out.println("Kiitos!");
    System.out.println(tuomari);
  }

  protected abstract void getToinenSiirto();

  protected static boolean onkoOkSiirto(String siirto) {
    return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
  }
}
