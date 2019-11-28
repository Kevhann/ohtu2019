package main.java.laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Summa extends Komento {
  private int valiSyote = 0;

  public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void suorita() {
    String s = syotekentta.getText();
    if (s.matches("[0-9]+")) {
      valiSyote = Integer.parseInt(s);

      sovellus.plus(valiSyote);
      tuloskentta.setText("" + (sovellus.tulos()));
    } else {
      syotekentta.setText("");
    }

  }

  @Override
  public void peru() {
    sovellus.miinus(valiSyote);
    tuloskentta.setText("" + (sovellus.tulos()));

  }

}