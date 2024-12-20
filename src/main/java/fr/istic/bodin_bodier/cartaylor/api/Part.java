package fr.istic.bodin_bodier.cartaylor.api;

import java.util.Optional;
import java.util.Set;

/**
 * Interface représentant une pièce dans le configurateur de véhicule.
 */
public interface Part extends Element {
  default String getName() {
    return this.getClass().getTypeName();
  }

  /**
   * Retourne la catégorie de la pièce.
   * 
   * @return la catégorie de la pièce
   */
  Category getCategory();

  /**
   * Retourne le type de la pièce.
   * 
   * @return le type de la pièce
   */
  PartType getType();

  /**
   * Retourne les noms des propriétés de la pièce.
   * 
   * @return les noms des propriétés de la pièce
   */
  Set<String> getPropertyNames();

  /**
   * Retourne la valeur d'une propriété de la pièce.
   * 
   * @param propertyName le nom de la propriété
   * @return la valeur de la propriété
   */
  Optional<String> getProperty(String propertyName);

  /**
   * Définit la valeur d'une propriété de la pièce.
   * 
   * @param propertyName  le nom de la propriété
   * @param propertyValue la valeur à définir
   */
  void setProperty(String propertyName, String propertyValue);

  /**
   * Retourne les valeurs possibles pour une propriété de la pièce.
   * 
   * @param propertyName le nom de la propriété
   * @return les valeurs possibles pour la propriété
   */
  Set<String> getAvailablePropertyValues(String propertyName);
}
