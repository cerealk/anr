package it.ck.anr.domain.fixtures;

import java.util.ArrayList;
import java.util.List;

import it.ck.anr.domain.Card;
import it.ck.anr.domain.Deck;
import it.ck.anr.domain.Identity;
import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.infrastructure.Event;

public class DeckBuilder {

  private List<Event> events = new ArrayList<>();

  public DeckBuilder(Identity identity) {
    events.add(new DeckCreatedEvent(identity));
  }
  
  public Deck build(){
    return new Deck(events);
  }

  public DeckBuilder with(int numberOfCopies, Card card) {
    for (int i = 0; i< numberOfCopies; i++)
      events.add(new CardAddedEvent(card));
    return this;
  }

}
