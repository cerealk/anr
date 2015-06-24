package it.ck.anr.domain;

import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.infrastructure.Event;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DeckTest {

  @Test
  public void aDeckIsCreatedStartingFromAnIdentity(){
    Identity identity = identity();
    Deck deck = new Deck(identity);

    assertThat(deck.changes(), contains((Event) new DeckCreatedEvent(identity)));
  }

  @Test
  public void aDecCanBeCreatedStartingFromEvents(){
    List<Event> eventList = new ArrayList<Event>();
    eventList.add(new DeckCreatedEvent(identity()));
    Deck deck = new Deck(eventList);


  }

  @Test
  public void iCanAddACard(){
    Deck deck = new Deck(identity());
    Card card = new Card();
    deck.add(card);

    assertThat(deck.changes(), hasItems((Event) new CardAddedEvent(card)));
  }

  private Identity identity() {
    return new Identity();
  }


}
