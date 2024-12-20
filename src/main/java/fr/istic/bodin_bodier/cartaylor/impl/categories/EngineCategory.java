package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

/**
 * Classe représentant la catégorie des moteurs.
 */
public class EngineCategory extends AbstractCategory {

  /**
   * Enumération des types de carburant disponibles.
   */
  public enum FuelType {
    Gasoline,
    Diesel,
    Hybrid
  }

  /**
   * Puissance du moteur en kW.
   */
  private int power; // en kW

  /**
   * Type de carburant du moteur.
   */
  private FuelType fuelType; // "Gasoline", "Diesel", "Hybrid"

  /**
   * Constructeur de la catégorie des moteurs.
   */
  public EngineCategory() {
    super("Engine");
    this.power = 80;
    this.fuelType = FuelType.Diesel;
  }

  /**
   * Retourne le type de carburant du moteur.
   * 
   * @return le type de carburant du moteur
   */
  public FuelType getFuelType() {
    return fuelType;
  }

  /**
   * Retourne la puissance du moteur en kW.
   * 
   * @return la puissance du moteur en kW
   */
  public int getPower() {
    return power;
  }
}
