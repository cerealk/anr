package it.ck.anr.domain;

import static it.ck.anr.domain.Faction.ANARCH;
import static it.ck.anr.domain.Faction.JINTEKI;
import static it.ck.anr.domain.Faction.SHAPER;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Test;

import it.ck.anr.domain.fixtures.CardBuilder;

public class IdentityTest {

  private static final int MINIMUM_DECK_SIZE = 5;
  private static final int REPUTATION = 10;
  private Identity anarchIdentity = new Identity("identityName", ANARCH, MINIMUM_DECK_SIZE, REPUTATION);

  @Test
  public void testSameSide(){
    Card runnerCard = new CardBuilder(SHAPER).build();
    assertThat(anarchIdentity.hasSameSideAs(runnerCard), is(true));
    
    Card corpCard = new CardBuilder(JINTEKI).build();
    assertThat(anarchIdentity.hasSameSideAs(corpCard), is(false));
  }

  @Test
  public void testSameFaction() {
    Card anarchCard = new CardBuilder(ANARCH).build();
    assertThat(anarchIdentity.hasSameFactionAs(anarchCard), is(true));

    Card shaperCard = new CardBuilder(SHAPER).build();
    assertThat(anarchIdentity.hasSameFactionAs(shaperCard), is(false));
  }
}
