package it.ck.anr.infrastructure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class Aggregate {

  public static class NullEventException extends RuntimeException{
    private static final long serialVersionUID = 8594298824155772481L;
  }
  
  public static class UnsupportedEventException extends RuntimeException{
    private static final long serialVersionUID = 1802524654433100789L;
  }

  private List<Event> uncommittedChanges = new ArrayList<Event>();

  public Aggregate(){}

  public void pastEvents(List<Event> eventList) {
    for(Event evt : eventList){
      doApply(evt);
    }
  }

  public final void apply(Event event) {
    if(event == null)
      throw new NullEventException();

    doApply(event);

    this.uncommittedChanges.add(event);
  }

  //TODO: separare lookup e invocazione del metodo. Gestire meglio le eccezioni
  //TODO: probabilmente dovrebbe essere inserito un oggetto per separare il dominio dall'oggetot Method
  private void doApply(Event event) {
    try {
      Method doApplyMethod = this.getClass().getDeclaredMethod("doApply", event.getClass());
      doApplyMethod.invoke(this, event);
    } catch (NoSuchMethodException e) {
      //the event is not relevant to this aggreagte
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
