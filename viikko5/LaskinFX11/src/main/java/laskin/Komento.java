package main.java.laskin;

import laskin.Sovelluslogiikka;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {

  protected TextField tuloskentta;
  protected TextField syotekentta;
  protected Sovelluslogiikka sovellus;

  public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
    this.syotekentta = syotekentta;
    this.tuloskentta = tuloskentta;
    this.sovellus = sovellus;

  }

  public abstract void suorita();

  public abstract void peru();
}
