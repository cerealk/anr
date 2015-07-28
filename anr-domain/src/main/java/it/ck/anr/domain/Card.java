package it.ck.anr.domain;

public class Card {

  
  private final CardId id;
  private final Faction faction;
  private final boolean limited;

  public Card(CardId id, Faction faction, boolean limited) {
    this.id = id;
    this.faction = faction;
    this.limited = limited;
  }

  public Side side() { return faction.side(); }

  public boolean isLimited() {
    return limited;
  }

  public Faction faction() {
    return faction;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Card other = (Card) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  
}
