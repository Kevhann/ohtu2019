package statistics;

import java.util.List;
import java.util.ArrayList;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {

  private ArrayList<Matcher> list;

  public QueryBuilder() {
    this.list = new ArrayList<>();
  }

  public QueryBuilder playsIn(String t) {
    list.add(new PlaysIn(t));
    return this;
  }

  public QueryBuilder hasAtLeast(int v, String s) {
    list.add(new HasAtLeast(v, s));
    return this;
  }

  public QueryBuilder hasFewerThan(int v, String s) {
    list.add(new HasFewerThan(v, s));
    return this;
  }

  public QueryBuilder oneOf(Matcher... matchers) {
    list.add(new Or(matchers));
    return this;
  }

  public Matcher build() {
    if (list.isEmpty()) {
      return new All();
    }
    And and = new And(list.toArray(new Matcher[list.size()]));
    list = new ArrayList<>();
    return and;

  }
}