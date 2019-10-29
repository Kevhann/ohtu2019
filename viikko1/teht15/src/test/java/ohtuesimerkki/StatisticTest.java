package test.java.ohtuesimerkki;

import main.java.ohtuesimerkki.*;
import ohtuesimerkki.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StatisticTest {

  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<>();

      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri", "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));

      return players;
    }
  };

  Statistics stats;

  @Before
  public void setUp() {
    // luodaan Statistics-olio joka käyttää "stubia"
    stats = new Statistics(readerStub);
  }

  @Test
  public void joukkueenNimiPalauttaa() {
    assertEquals(3, stats.team("EDM").size());
  }

  @Test
  public void pelaajaaEiLoydyVaarallaNimella() {
    assertNull(stats.search("LMAO"));
  }

  @Test
  public void pelaajaLoytyyNimella() {
    assertEquals("Kurri", stats.search("Kurri").getName());
  }

  @Test
  public void joukkueenNimiPalauttaaTyhjan() {
    assertTrue(stats.team("LMAO").isEmpty());
  }

  @Test
  public void topScorersPalauttaaParhaan() {
    assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
  }
}