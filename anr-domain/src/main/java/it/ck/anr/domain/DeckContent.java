package it.ck.anr.domain;

import java.util.ArrayList;
import java.util.List;

public class DeckContent {
  private List<Card> cards;

  public DeckContent() {
    this.cards = new ArrayList<Card>();
  }

  public int count(Card card) {
    int count = 0;
    if (cards.contains(card)){
      for(Card c : cards){
        if(c.equals(card)) count++;
      }
    }
    return count;
  }

  public void add(Card card) {
    this.cards.add(card);
  }

  public int size() {
    return cards.size();
  }
  
  public List<Card> outOfFaction(Identity identity){
    List<Card> outOfFactionsCard = new ArrayList<Card>();
    for(Card card : cards){
      if(!identity.hasSameFactionAs(card))
        outOfFactionsCard.add(card);
    }
    
    return outOfFactionsCard;
  }
}