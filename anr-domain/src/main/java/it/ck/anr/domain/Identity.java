package it.ck.anr.domain;

public class Identity {
  private final Side side;

  public Identity(Side side) {
    this.side = side;
  }

  public Side side() {
    return side;
  }
}
