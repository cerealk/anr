package it.ck.anr.domain;

public class Card {
  private final Side side;
  private boolean limited;

  public Card(Side side, boolean limited) {
    this.side = side;
    this.limited = limited;
  }

  public Side side() { return side; }

  public boolean isLimited() {
    return limited;
  }
  
  
}
