package it.ck.anr.domain.events;

import it.ck.anr.domain.Identity;
import it.ck.anr.infrastructure.Event;

public class DeckCreatedEvent implements Event{
  private final Identity identity;

  public DeckCreatedEvent(Identity identity) {
    this.identity = identity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DeckCreatedEvent)) return false;

    DeckCreatedEvent that = (DeckCreatedEvent) o;

    return !(identity != null ? !identity.equals(that.identity) : that.identity != null);

  }

  @Override
  public int hashCode() {
    return identity != null ? identity.hashCode() : 0;
  }
}
