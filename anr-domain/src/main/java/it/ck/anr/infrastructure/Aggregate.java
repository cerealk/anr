package it.ck.anr.infrastructure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class Aggregate {

  private List<Event> uncommittedChanges = new ArrayList<Event>();

  public Aggregate(){}

  public Aggregate(List<Event> eventList) {
    for(Event evt : eventList){
      doApply(evt);
    }
  }

  public static class NullEventException extends RuntimeException{
  }

  public static class UnsupportedEventException extends RuntimeException{
  }

  public final void apply(Event event) {
    if(event == null)
      throw new NullEventException();

    doApply(event);

    this.uncommittedChanges.add(event);
  }

  private void doApply(Event event) {
    try {
      Method doApplyMethod = this.getClass().getDeclaredMethod("doApply", event.getClass());
      doApplyMethod.invoke(this, event);
    } catch (NoSuchMethodException e) {
    } catch (InvocationTargetException e) {
      throw new UnsupportedEventException();
    } catch (IllegalAccessException e) {
      throw new UnsupportedEventException();
    }
  }

  public List<Event> changes() {
    return uncommittedChanges;
  }
}
