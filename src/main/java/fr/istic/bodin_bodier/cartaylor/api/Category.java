package fr.istic.bodin_bodier.cartaylor.api;

/**
 * Interface représentant une catégorie de pièces dans le configurateur de
 * voiture.
 * 
 * <p>
 * Une catégorie permet d'organiser et de regrouper les différents types de
 * pièces
 * disponibles dans le configurateur. Chaque catégorie est identifiée par un nom
 * unique.
 * 
 * <p>
 * Exemples de catégories :
 * <ul>
 * <li>Moteur</li>
 * <li>Transmission</li>
 * <li>Couleur</li>
 * <li>Intérieur</li>
 * </ul>
 * 
 * @see Category
 */
public interface Category extends Element {

  /**
   * Retourne le nom de la catégorie.
   * 
   * @return le nom unique identifiant la catégorie
   */
  String getName();
}