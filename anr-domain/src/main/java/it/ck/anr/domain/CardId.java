package it.ck.anr.domain;

public class CardId {

  public class EmptySetException extends RuntimeException {
    private static final long serialVersionUID = -8124510053952969697L;
  }
  
  public class EmptyCardNumberException extends RuntimeException {
    private static final long serialVersionUID = -8124510053952969697L;
  }

  private final String setCode;
  private final String cardNumber;

  public CardId(String setCode, String cardNumber) {
    
    if(setCode == null || setCode.length() == 0)
      throw new EmptySetException();
    this.setCode = setCode;
    if(cardNumber == null || cardNumber.length() == 0)
      throw new EmptyCardNumberException();
    this.cardNumber = cardNumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
    result = prime * result + ((setCode == null) ? 0 : setCode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CardId other = (CardId) obj;
    if (cardNumber == null) {
      if (other.cardNumber != null)
        return false;
    } else if (!cardNumber.equals(other.cardNumber))
      return false;
    if (setCode == null) {
      if (other.setCode != null)
        return false;
    } else if (!setCode.equals(other.setCode))
      return false;
    return true;
  }


}
