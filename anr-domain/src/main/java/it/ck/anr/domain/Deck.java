package it.ck.anr.domain;

import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.infrastructure.Aggregate;
import it.ck.anr.infrastructure.Event;

import java.util.List;

public class Deck extends Aggregate {
  public Deck(Identity identity) {
    super();
    apply(new DeckCreatedEvent((identity)));
  }

  public Deck(List<Event> eventList) {
    super(eventList);
  }

  public void add(Card card) {
    apply(new CardAddedEvent(card));
  }
}
