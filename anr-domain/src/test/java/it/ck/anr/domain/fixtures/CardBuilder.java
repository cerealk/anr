package it.ck.anr.domain.fixtures;

import static it.ck.anr.domain.Faction.ANARCH;

import it.ck.anr.domain.Card;
import it.ck.anr.domain.CardId;
import it.ck.anr.domain.Faction;

public class CardBuilder {

  private Faction faction = ANARCH;
  private boolean limited = false;
  private CardId id;

  public CardBuilder(CardId id){
    this.id = id;
  }
  
  public CardBuilder(Faction faction) {
    this.faction = faction;
  }
  
  public CardBuilder ofFaction(Faction faction){
    this.faction = faction;
    return this;
  }
  
  public CardBuilder limited(){
    this.limited = true;
    return this;
  }

  public Card build(){
    return new Card(id, faction, limited);
  }
}
