package it.ck.anr.domain;

import java.util.ArrayList;
import java.util.List;

public class DeckContent {
  public List<Card> cards;

  public DeckContent() {
    this.cards = new ArrayList<Card>();
  }

  public int cardCount(Card card) {
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
}