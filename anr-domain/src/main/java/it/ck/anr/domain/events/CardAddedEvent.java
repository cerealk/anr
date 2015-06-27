package it.ck.anr.domain.events;

import it.ck.anr.domain.Card;
import it.ck.anr.infrastructure.Event;

public class CardAddedEvent implements Event {


  private final Card card;

  public CardAddedEvent(Card card) {
    this.card = card;
  }

  public Card getCard() {
    return card;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardAddedEvent)) return false;

    CardAddedEvent that = (CardAddedEvent) o;

    return !(card != null ? !card.equals(that.card) : that.card != null);

  }

  @Override
  public int hashCode() {
    return card != null ? card.hashCode() : 0;
  }
}
