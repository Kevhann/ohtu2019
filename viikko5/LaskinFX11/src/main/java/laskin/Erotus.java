package main.java.laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Erotus extends Komento {
  private int valiSyote = 0;

  public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void suorita() {
    String s = syotekentta.getText();
    if (s.matches("[0-9]+")) {
      int a = Integer.parseInt(s);
      valiSyote = Integer.parseInt(s);
      sovellus.miinus(a);
      int tulos = sovellus.tulos();
      tuloskentta.setText("" + tulos);

    } else {
      syotekentta.setText("");
    }
  }

  @Override
  public void peru() {
    sovellus.plus(valiSyote);
    tuloskentta.setText("" + (sovellus.tulos()));

  }
}