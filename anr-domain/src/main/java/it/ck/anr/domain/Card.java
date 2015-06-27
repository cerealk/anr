package it.ck.anr.domain;

public class Card {
  private final Side side;

  public Card(Side side) {
    this.side = side;
  }

  public Side side() { return side; }
}
