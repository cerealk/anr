package it.ck.anr.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import it.ck.anr.domain.fixtures.CardBuilder;

public class CardTest {

  @Test
  public void twoCardsAreEqualsIfTheyShareTheSameId() {
    CardId id = new CardId("001", "01");
    Card card1 = new CardBuilder(id).build();
    Card card2 = new CardBuilder(id).build();
    
    assertThat(card1, is(equalTo(card2)));
  }

}
