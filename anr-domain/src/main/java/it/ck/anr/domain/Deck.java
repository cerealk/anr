package it.ck.anr.domain;

import java.util.List;

import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.infrastructure.Aggregate;
import it.ck.anr.infrastructure.Event;

public class Deck extends Aggregate {
  private Identity identity;
  private DeckContent cards = new DeckContent();

  public class SideMismatchException extends RuntimeException {
  }

  public class TooManyCardsException extends RuntimeException {
  }
  
  public Deck(Identity identity) {
    super();
    apply(new DeckCreatedEvent(identity));
  }

  public Deck(List<Event> eventList) {
    super(eventList);
  }

  public void add(Card card) {
    canAdd(card);
    apply(new CardAddedEvent(card));
  }

  private void canAdd(Card card) {
    if (!identity().hasSameSideAs(card))
      throw new SideMismatchException();
    
    if (cards.cardCount(card) >= 3)
      throw new TooManyCardsException();
  }

  private Identity identity() {
    return identity;
  }

  public boolean isValid() {
    return this.size() >= identity.minimumSize();
  }

  private int size() {
    return cards.cards.size();
  }

  public void doApply(DeckCreatedEvent event) {
    this.identity = event.getIdentity();
  }

  public void doApply(CardAddedEvent event) {
    this.cards.add(event.getCard());
  }

}
