package it.ck.anr.domain;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CardIdTest {

  private static final String EMPTY_CARD_NUMBER = "";
  private static final String EMPTY_SET_CODE = EMPTY_CARD_NUMBER;
  private static final String NULL_SET_CODE = null;
  private static final String NULL_CARD_NUMBER = null;
  
  @Rule public ExpectedException expected = ExpectedException.none();
  
  @Test
  public void aCardIdAlwaysHaveANonEmptySet() {
    expected.expect(CardId.EmptySetException.class);
    CardId emptySetCardId = new CardId(EMPTY_SET_CODE, "001");
  }
  
  @Test
  public void aCardIdAlwaysHaveANonNullSet(){
    expected.expect(CardId.EmptySetException.class);
    CardId nullSetCardId = new CardId(NULL_SET_CODE, "001");
  }
  
  @Test
  public void aCardIdAlwaysHaveANonEmptyCardNumber() {
    expected.expect(CardId.EmptyCardNumberException.class);
    CardId emptyCardNumberCardId = new CardId("01", EMPTY_CARD_NUMBER);
  }
  
  @Test
  public void aCardIdAlwaysHaveANonNullEmptyCardNumber() {
    expected.expect(CardId.EmptyCardNumberException.class);
    CardId emptyCardNumberCardId = new CardId("01", NULL_CARD_NUMBER);
  }

}
