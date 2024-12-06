package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class EngineCategory extends AbstractCategory {
  public EngineCategory() {
    super("Engine");
  }

  // Propriétés spécifiques aux moteurs
  private int power; // en kW
  private String fuelType; // "Gasoline", "Diesel", "Hybrid"
}