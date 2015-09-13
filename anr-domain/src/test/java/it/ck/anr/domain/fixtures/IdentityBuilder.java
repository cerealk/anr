package it.ck.anr.domain.fixtures;

import static it.ck.anr.domain.Faction.JINTEKI;

import it.ck.anr.domain.Faction;
import it.ck.anr.domain.Identity;

public class IdentityBuilder {

  private int minimumSize = 2;
  private int influence = 10;
  private Faction faction  = JINTEKI;
  
  
  public Identity build() {
    return new Identity("test Identity", faction, minimumSize, influence);
  }

  public IdentityBuilder withInfluence(int influence) {
    this.influence = influence;
    return this;
  }

  public IdentityBuilder withFaction(Faction faction) {
    this.faction = faction;
    return this;
  }
  
}
