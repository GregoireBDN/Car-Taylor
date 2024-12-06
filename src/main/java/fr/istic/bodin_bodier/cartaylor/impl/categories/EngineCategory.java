package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class EngineCategory extends AbstractCategory {

  public enum FuelType {
    Gasoline,
    Diesel,
    Hybrid
  }

  // Propriétés spécifiques aux moteurs
  private int power; // en kW
  private FuelType fuelType; // "Gasoline", "Diesel", "Hybrid"

  public EngineCategory() {
    super("Engine");
    this.power = 80;
    this.fuelType = FuelType.Diesel;
  }


  public FuelType getFuelType() {
    return fuelType;
  }

  public int getPower() {
    return power;
  }
}
