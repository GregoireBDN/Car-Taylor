package fr.istic.bodin_bodier.cartaylor.api;

import java.util.Set;

/**
 * Interface représentant une configuration de véhicule dans le configurateur.
 * 
 * <p>
 * Une configuration est un ensemble de sélections de pièces qui doivent
 * respecter les règles de compatibilité et d'exigences définies.
 * 
 * @see Configuration
 */
public interface Configuration {
  /**
   * Vérifie si la configuration actuelle est valide selon les règles
   * de compatibilité et d'exigences.
   * 
   * @return true si la configuration est valide, false sinon
   */
  boolean isValid();

  /**
   * Vérifie si la configuration est complète, c'est-à-dire si une pièce
   * a été sélectionnée pour chaque catégorie requise.
   * 
   * @return true si la configuration est complète, false sinon
   */
  boolean isComplete();

  /**
   * Retourne l'ensemble des pièces actuellement sélectionnées
   * dans la configuration.
   * 
   * @return l'ensemble des pièces sélectionnées
   */
  Set<PartType> getSelectedParts();

  /**
   * Sélectionne une pièce pour sa catégorie correspondante.
   * 
   * @param chosenPart la pièce à sélectionner
   * @throws IllegalArgumentException si la pièce est null
   */
  void selectPart(PartType chosenPart);

  /**
   * Retourne la pièce sélectionnée pour une catégorie donnée.
   * 
   * @param category la catégorie pour laquelle obtenir la sélection
   * @return la pièce sélectionnée pour la catégorie, ou null si aucune sélection
   * @throws IllegalArgumentException si la catégorie est null
   */
  PartType getSelectionForCategory(Category category);

  /**
   * Désélectionne la pièce pour une catégorie donnée.
   * 
   * @param categoryToClear la catégorie à désélectionner
   * @throws IllegalArgumentException si la catégorie est null
   */
  void unselectPartType(Category categoryToClear);

  /**
   * Efface toutes les sélections de la configuration.
   */
  void clear();

  /**
   * Retourne le prix total de la configuration.
   * 
   * @return le prix total de la configuration
   */
  int getTotalPrice();
}
