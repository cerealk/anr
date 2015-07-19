package it.ck.anr.domain;

import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.domain.fixtures.CardBuilder;
import it.ck.anr.infrastructure.Event;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static it.ck.anr.domain.Side.*;
import static it.ck.anr.domain.Side.CORP;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DeckTest {

  public static final int MINIMUM_SIZE = 2;
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Identity identity = new Identity(CORP, MINIMUM_SIZE);
  private Deck deck = new Deck(identity);

  @Test
  public void aDeckIsCreatedStartingFromAnIdentity(){
    assertThat(deck.changes(), contains((Event) new DeckCreatedEvent(identity)));
  }

  @Test
  public void aDeckCanBeCreatedStartingFromEvents(){
    List<Event> eventList = new ArrayList<Event>();
    eventList.add(new DeckCreatedEvent(identity));
    Deck deck = new Deck(eventList);
    assertThat(deck.changes(), hasSize(0));
  }

  @Test
  public void iCanAddACard(){
    Card card = getCorpCard();
    deck.add(card);

    assertThat(deck.changes(), hasItems((Event) new CardAddedEvent(card)));
  }

  @Test
  public void iCanAddonlyCardsOfTheFactionOfTheIdentity(){
    expectedException.expect(Deck.SideMismatchException.class);
    deck.add(getRunnerCard());
  }

  @Test
  public void aDeckIsValidIfItHasAtLeastTheMinimumSizeForTheIdentity(){
    deck.add(getCorpCard());
    deck.add(getCorpCard());

    assertThat(deck.isValid(), is(true));
  }


  @Test
  public void iCanHaveMaxThreeCardsOfTheSameTypePerDeck(){

    expectedException.expect(Deck.TooManyCardsException.class);

    Deck deck = new Deck(identity);
    Card card = getCorpCard();
    deck.add(card);
    deck.add(card);
    deck.add(card);
    deck.add(card);

  }
  
  @Test
  public void someCardsAreLimitedOnePerDeck(){
    Deck deck = new Deck(identity);
    Card limitedCard = getLimitedCard();
    
    expectedException.expect(Deck.TooManyCardsException.class);
    
    deck.add(limitedCard);
    deck.add(limitedCard);
    
  }

  private Card getLimitedCard() {
    CardBuilder builder = new CardBuilder(CORP);
    return builder.limited().build();
  }

  private Card getCorpCard() {
    return  new CardBuilder(CORP).build();
  }

  private Card getRunnerCard() {
    return new CardBuilder(RUNNER).build();
  }

}
