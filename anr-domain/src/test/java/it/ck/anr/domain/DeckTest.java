package it.ck.anr.domain;

import static it.ck.anr.domain.Faction.ANARCH;
import static it.ck.anr.domain.Faction.JINTEKI;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.ck.anr.domain.events.CardAddedEvent;
import it.ck.anr.domain.events.DeckCreatedEvent;
import it.ck.anr.domain.fixtures.CardBuilder;
import it.ck.anr.domain.fixtures.DeckBuilder;
import it.ck.anr.infrastructure.Event;

public class DeckTest {

  public static final int MINIMUM_SIZE = 2;
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Identity identity = new Identity("test Identity", JINTEKI, MINIMUM_SIZE);
  private Deck deck = new Deck(identity);

  @Test
  public void aDeckIsCreatedStartingFromAnIdentity(){
    assertThat(deck.changes(), contains((Event) new DeckCreatedEvent(identity)));
  }

  @Test
  public void iCanAddACard(){
    Card card = corpCard();
    deck.add(card);

    assertThat(deck.changes(), hasItems((Event) new CardAddedEvent(card)));
  }

  @Test
  public void iCanAddonlyCardsOfTheFactionOfTheIdentity(){
    expectedException.expect(Deck.SideMismatchException.class);
    deck.add(runnerCard());
  }

  @Test
  public void aDeckIsValidIfItHasAtLeastTheMinimumSizeForTheIdentity(){
    deck.add(corpCard());
    deck.add(corpCard());

    assertThat(deck.isValid(), is(true));
  }


  @Test
  @Ignore
  public void iCanHaveMaxThreeCardsOfTheSameTypePerDeck(){

    expectedException.expect(Deck.TooManyCardsException.class);
    
    given(aDeck().with(3, corpCard()));
    
    deck.add(corpCard());

  }
  
  private void given(DeckBuilder deckBuilder){
    deck = deckBuilder.build();
  }

  private DeckBuilder aDeck() {
    return new DeckBuilder(identity);
  }
  
  @Test
  public void someCardsAreLimitedOnePerDeck(){
    Card limitedCard = getLimitedCard();
    
    expectedException.expect(Deck.TooManyCardsException.class);
    
    deck.add(limitedCard);
    deck.add(limitedCard);
    
  }
  
//  @Test
//  public void influenceLimitsExtraFactionCard(){
//    
//  }

  private Card getLimitedCard() {
    CardBuilder builder = new CardBuilder(JINTEKI);
    return builder.limited().build();
  }

  private Card corpCard() {
    return  new CardBuilder(JINTEKI).build();
  }

  private Card runnerCard() {
    return new CardBuilder(ANARCH).build();
  }

}
