package it.ck.anr.domain.fixtures;

import it.ck.anr.domain.Card;
import it.ck.anr.domain.Side;

public class CardBuilder {

  private Side side;
  private boolean limited;

  public CardBuilder(Side side) {
    this.side = side;
  }
  
  public CardBuilder limited(){
    this.limited = true;
    return this;
  }

  public Card build(){
    return new Card(side, limited);
  }
}
