package fr.istic.bodin_bodier.cartaylor.api;

import java.io.PrintStream;
import java.util.Set;

/**
 * Interface principale du configurateur de véhicule.
 * 
 * <p>
 * Cette interface fournit les méthodes nécessaires pour :
 * <ul>
 * <li>Accéder aux catégories disponibles</li>
 * <li>Gérer les types de pièces</li>
 * <li>Manipuler la configuration</li>
 * <li>Vérifier les compatibilités</li>
 * </ul>
 * 
 * @see Configurator
 */
public interface Configurator {
  /**
   * Retourne l'ensemble des catégories disponibles dans le configurateur.
   * 
   * @return l'ensemble des catégories
   */
  Set<Category> getCategories();

  /**
   * Retourne l'ensemble des types de pièces disponibles dans le configurateur.
   * 
   * @return l'ensemble des types de pièces
   */
  Set<PartType> getPartTypes();

  /**
   * Retourne l'ensemble des variantes disponibles pour une catégorie donnée.
   * 
   * @param category la catégorie pour laquelle obtenir les variantes
   * @return l'ensemble des types de pièces pour cette catégorie
   * @throws IllegalArgumentException si la catégorie est null
   */
  Set<PartType> getVariants(Category category);

  /**
   * Retourne la configuration actuelle du véhicule.
   * 
   * @return la configuration en cours
   */
  Configuration getConfiguration();

  /**
   * Retourne le vérificateur de compatibilité utilisé par le configurateur.
   * 
   * @return le vérificateur de compatibilité
   */
  CompatibilityChecker getCompatibilityChecker();

  /**
   * Retourne le catalogue utilisé par le configurateur.
   * 
   * @return le catalogue
   */
  void printDescription(PrintStream stream);
}
