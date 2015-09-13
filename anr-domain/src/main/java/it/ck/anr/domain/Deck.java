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
    private static final long serialVersionUID = -5944143115034905975L;
  }

  public class TooManyCardsException extends RuntimeException {
    private static final long serialVersionUID = 893903652275064344L;
  }
  
  public class InfluenceExceededException extends RuntimeException {
    private static final long serialVersionUID = 3813756562848705945L;
  }
  
  public Deck(Identity identity) {
    apply(new DeckCreatedEvent(identity));
  }

  public Deck(List<Event> eventList) {
    pastEvents(eventList);
  }

  public void add(Card card) {
    canAdd(card);
    apply(new CardAddedEvent(card));
  }

  private void canAdd(Card card) {
    if (!identity().hasSameSideAs(card))
      throw new SideMismatchException();
    
    if (cards.count(card) >= 3 || (cards.count(card) >=1) && card.isLimited())
      throw new TooManyCardsException();
    
    if(!identity().hasSameFactionAs(card)){
      if(influence() + card.influenceCost() > identity().getInfluence()){
        throw new InfluenceExceededException();
      }
    }
  }

  private int influence() {
    List<Card> outOfFactionCards = cards.outOfFaction(identity());
    int influence = 0;
    for(Card card :outOfFactionCards)
      influence += card.influenceCost();
    
    return influence;
  }

  private Identity identity() {
    return identity;
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

  public void doApply(CardAddedEvent event) {
    this.cards.add(event.getCard());
  }

}
