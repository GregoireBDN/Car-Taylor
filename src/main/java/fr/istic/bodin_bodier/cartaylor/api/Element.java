package fr.istic.bodin_bodier.cartaylor.api;

/**
 * Interface représentant un élément dans le configurateur de véhicule.
 */
public interface Element {
  /**
   * Accepte un visiteur pour l'impression de la description.
   * 
   * @param visitor le visiteur
   */
  void accept(Visitor visitor);
}
