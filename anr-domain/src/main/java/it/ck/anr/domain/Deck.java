package it.ck.anr.domain;

import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.infrastructure.Aggregate;
import it.ck.anr.infrastructure.Event;

import java.util.ArrayList;
import java.util.List;

public class Deck extends Aggregate {
  private Identity identity;
  private List<Card> cards = new ArrayList<>();

  public class TooManyCardsException extends RuntimeException {
  }

  public class SideMismatchException extends RuntimeException {
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
    if (!this.identity.side().equals(card.side()))
      throw new SideMismatchException();
  }

  public boolean isValid() {
    return this.size() >= identity.minimumSize();
  }

  private int size() {
    return cards.size();
  }

  public void doApply(DeckCreatedEvent event) {
    this.identity = event.getIdentity();
  }

  public void doApply(CardAddedEvent event){
    this.cards.add(event.getCard());
  }




}
