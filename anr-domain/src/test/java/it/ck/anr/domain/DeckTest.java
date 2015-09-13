package it.ck.anr.domain;

import static it.ck.anr.domain.Faction.ANARCH;
import static it.ck.anr.domain.Faction.JINTEKI;
import static it.ck.anr.domain.Faction.WAYLAND;
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
import it.ck.anr.domain.fixtures.*;
import it.ck.anr.infrastructure.Event;

public class DeckTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Deck deck;

  @Test
  public void aDeckIsCreatedStartingFromAnIdentity(){
    Identity identity = new IdentityBuilder().build();
    deck = new Deck(identity);
    assertThat(deck.changes(), contains((Event) new DeckCreatedEvent(identity)));
  }

  @Test
  public void iCanAddACard(){
    Card card = corpCard();
    given(aDeck());
    deck.add(card);

    assertThat(deck.changes(), hasItems((Event) new CardAddedEvent(card)));
  }

  @Test
  public void iCanAddonlyCardsOfTheFactionOfTheIdentity(){
    
    given(aDeck());
    expectedException.expect(Deck.SideMismatchException.class);
    deck.add(runnerCard());
  }

  @Test
  public void aDeckIsValidIfItHasAtLeastTheMinimumSizeForTheIdentity(){
    given(aDeck());
    deck.add(corpCard());
    assertThat(deck.isValid(), is(false));
    
    
    deck.add(corpCard());
    assertThat(deck.isValid(), is(true));
  }


  @Test
  public void iCanHaveMaxThreeCardsOfTheSameTypePerDeck(){

    expectedException.expect(Deck.TooManyCardsException.class);
    
    given(aDeck().with(3, corpCard()));
    
    deck.add(corpCard());

  }
  
  private void given(DeckBuilder deckBuilder){
    deck = deckBuilder.build();
  }

  private DeckBuilder aDeck() {
    return new DeckBuilder(new IdentityBuilder().build());
  }
  
  @Test
  public void someCardsAreLimitedOnePerDeck(){
    
    given(aDeck());
    Card limitedCard = getLimitedCard();
    
    expectedException.expect(Deck.TooManyCardsException.class);
    
    deck.add(limitedCard);
    deck.add(limitedCard);
    
  }
  
  @Test
  public void influenceLimitsExtraFactionCard(){
    given(new DeckBuilder(new IdentityBuilder().withFaction(JINTEKI).withInfluence(2)));
    
    expectedException.expect(Deck.InfluenceExceededException.class);
    
    deck.add(aCard(WAYLAND).withInfluenceCost(2).build());
    deck.add(aCard(WAYLAND).withInfluenceCost(2).build());
  }

  private CardBuilder aCard(Faction faction) {
    
    return new CardBuilder(faction);
  }

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
