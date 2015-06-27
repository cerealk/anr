package it.ck.anr.infrastructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AggregateBehaviouralTest {

  private class TestAggregate extends Aggregate {

    private int eventCount;

    public TestAggregate(){
      super();
    }

    public TestAggregate(List<Event> eventList) {
      super(eventList);
    }

    protected void doApply(TestEvent event){
      this.eventCount++;
    }

    public boolean hasEventApplied(){
      return eventCount >0;
    }

    public int eventCount(){return eventCount;}

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

  @Test
  public void iCanCreateAnAggregateStartingFromItsEvents(){
    List<Event> events = new ArrayList<Event>();
    events.add(new TestEvent());
    events.add(new TestEvent());

    TestAggregate aggregate = new TestAggregate(events);

    assertThat(aggregate.changes(), is(empty()));
    assertThat(aggregate.eventCount(), is(2));
  }

}
