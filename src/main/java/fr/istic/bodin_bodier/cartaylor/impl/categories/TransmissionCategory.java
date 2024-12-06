package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class TransmissionCategory extends AbstractCategory {
  public TransmissionCategory() {
    super("Transmission");
  }

  // Propriétés spécifiques à la transmission
  private String type; // "Manual", "Automatic", "Sequential"
  private int gears;
  private boolean fourWheelDrive;
}