package it.ck.anr.domain;

import static it.ck.anr.domain.Side.CORP;
import static it.ck.anr.domain.Side.RUNNER;

public enum Faction {
  ANARCH(RUNNER),
  SHAPER(RUNNER),
  CRIMINAL(RUNNER),
  WAYLAND(CORP),
  JINTEKI(CORP),
  NBN(CORP),
  HAAS_BIOROID(CORP);
  
  private Side side;

  Faction(Side side){
    this.side = side;
    this.side = side;
  }
  
  public Side side(){
    return side;
  }
}
