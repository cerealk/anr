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
}