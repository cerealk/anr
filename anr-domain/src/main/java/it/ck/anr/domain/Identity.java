package it.ck.anr.domain;

public class Identity {
  private final int minimumSize;
  private final String name;
  private final Faction faction;
  private int influence;

  public Identity(String name, Faction faction, int minimumDeckSize, int influence) {
    this.name = name;
    this.faction = faction;
    this.minimumSize = minimumDeckSize;
    this.influence = influence;
  }

  public Side side() {
    return faction.side();
  }

  public int minimumSize() {
    return minimumSize;
  }

  public boolean hasSameSideAs(Card card) {
    return side().equals(card.side());
  }

  public boolean hasSameFactionAs(Card card) {
    return this.faction.equals(card.faction());
  }

  public int getInfluence() {
    return influence;
  }
}
