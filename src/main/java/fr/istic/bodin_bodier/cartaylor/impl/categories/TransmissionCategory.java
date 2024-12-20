package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

/**
 * Classe représentant la catégorie des transmissions.
 */
public class TransmissionCategory extends AbstractCategory {

  /**
   * Enumération des types de transmission disponibles.
   */
  enum TransmissionType {
    MANUAL, AUTOMATIC, SEQUENTIAL
  }

  private TransmissionType type;

  public TransmissionCategory() {
    super("Transmission");
    this.type = TransmissionType.MANUAL;
  }

  /**
   * Constructeur de la catégorie des transmissions.
   * 
   * @param type le type de transmission
   */
  public TransmissionCategory(TransmissionType type) {
    super("Transmission");
    this.type = type;
  }

  /**
   * Retourne le type de transmission.
   * 
   * @return le type de transmission
   */
  public TransmissionType getType() {
    return type;
  }
}
