package it.ck.anr.infrastructure;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AggregateBehaviouralTest {

//  @Test
//  public void givenACardICanAddItToADeck() {
//
//    Deck deck = new Deck();
//
//    CardId card = new CardId("1A");
//
////    deck.addCard(card);
//
//    AddCardEvent eventEmitted = null;
//    assertThat(eventEmitted, is(equalTo(new AddCardEvent(card))));
//
//  }



  private class TestAggregate extends Aggregate {

    private boolean eventApplied;

    protected void doApply(TestEvent event){
      this.eventApplied = true;
    }

    public boolean hasEventApplied(){
      return eventApplied;
    }

  }

  private class TestEvent implements Event {
  }

  private class UnlistenedEvent implements Event {
  }

  private TestAggregate testAggregate = new TestAggregate();

  private TestEvent testEvent = new TestEvent();

  @Test
  public void whenAnEventIsAppliedItsHandlerIsCalled(){
    Event evt = new TestEvent();
    testAggregate.apply(evt);

    assertThat(testAggregate.hasEventApplied(), is(true));
  }

  @Test
  public void someEventsAreNotUsed(){
   Event unlistenedEvent = new UnlistenedEvent();
    testAggregate.apply(unlistenedEvent);

    assertThat(testAggregate.hasEventApplied(), is(false));
  }
}
