package it.ck.anr.infrastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;

public class AggregateChangeTest {

  @Rule
  public ExpectedException expectedException = none();

  private Aggregate aggregate = new Aggregate() {
  };

  @Test
  public void aNewAggregateHasZeroChanges(){
    assertThat(aggregate.changes(), hasSize(0));
  }

  @Test
  public void whenAnEventIsAppliedTheAggregateChange(){
    Event evt = new Event(){};

    aggregate.apply(evt);

    assertThat(aggregate.changes(), hasSize(1));
    assertThat(aggregate.changes(), contains(evt));
  }

  @Test
  public void aNullEventCantBeApplied(){

    expectedException.expect(Aggregate.NullEventException.class);

    aggregate.apply(null);

    assertThat(aggregate.changes(), hasSize(0));
  }

  @Test
  public void anAggregateCanBeCratedStartingFromEvents(){
    List<Event> events = new ArrayList<Event>();
    events.add(new Event(){});
    events.add(new Event(){});

    Aggregate aggregate = new Aggregate(events) {
      public void doApply(Event event){

      };
    };
  }
}
