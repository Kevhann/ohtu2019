package main.java.laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Nollaa extends Komento {
  private int tulos = 0;

  public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void suorita() {
    tulos = sovellus.tulos();

    sovellus.nollaa();
    tuloskentta.setText("0");
    syotekentta.setText("");
  }

  @Override
  public void peru() {
    sovellus.plus(tulos);
    tuloskentta.setText("" + tulos);

  }
}
