package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class TransmissionCategory extends AbstractCategory {

  enum TransmissionType {
    MANUAL, AUTOMATIC, SEQUENTIAL
  }

  // Propriétés spécifiques à la transmission
  private TransmissionType type;

  public TransmissionCategory() {
    super("Transmission");
    this.type = TransmissionType.MANUAL;
  }

  public TransmissionCategory(TransmissionType type) {
    super("Transmission");
    this.type = type;
  }

  public TransmissionType getType() {
    return type;
  }
}
