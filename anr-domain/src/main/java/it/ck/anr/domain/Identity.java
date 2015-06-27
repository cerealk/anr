package it.ck.anr.domain;

public class Identity {
  private final Side side;
  private final int minimumSize;

  public Identity(Side side, int minimumSize) {
    this.side = side;
    this.minimumSize = minimumSize;
  }

  public Side side() {
    return side;
  }

  public int minimumSize() {
    return minimumSize;
  }
}
