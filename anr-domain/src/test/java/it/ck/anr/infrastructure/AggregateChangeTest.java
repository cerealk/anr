package it.ck.anr.infrastructure;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

  
   //TODO: meaningless test!!!!
  @Test
  public void anAggregateCanBeInitializedStartingFromEvents(){
    List<Event> events = new ArrayList<Event>();
    events.add(new Event(){});
    events.add(new Event(){});

    Aggregate aggregate = new Aggregate() {
      public void doApply(Event event){

      };
    };
    
    aggregate.pastEvents(events);
  }
}
