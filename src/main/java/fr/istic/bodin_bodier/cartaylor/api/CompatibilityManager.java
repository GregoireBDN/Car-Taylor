package fr.istic.bodin_bodier.cartaylor.api;

import java.util.Set;

/**
 * Interface définissant les opérations de gestion des compatibilités
 * et des exigences entre les différents types de pièces.
 * 
 * <p>
 * Cette interface étend CompatibilityChecker en ajoutant des
 * fonctionnalités de modification des règles de compatibilité.
 * 
 * @see CompatibilityManager
 */
public interface CompatibilityManager extends CompatibilityChecker {
  /**
   * Ajoute des incompatibilités entre une pièce de référence et un ensemble de
   * pièces cibles.
   * 
   * @param reference la pièce de référence
   * @param target    l'ensemble des pièces incompatibles avec la référence
   * @throws IllegalArgumentException si reference ou target est null
   */
  void addIncompatibilities(PartType reference, Set<PartType> target);

  /**
   * Supprime une incompatibilité entre deux pièces.
   * 
   * @param reference la pièce de référence
   * @param target    la pièce dont l'incompatibilité doit être supprimée
   * @throws IllegalArgumentException si reference ou target est null
   */
  void removeIncompatibility(PartType reference, PartType target);

  /**
   * Ajoute des exigences entre une pièce de référence et un ensemble de pièces
   * requises.
   * 
   * @param reference la pièce de référence
   * @param target    l'ensemble des pièces requises par la référence
   * @throws IllegalArgumentException si reference ou target est null
   */
  void addRequirements(PartType reference, Set<PartType> target);

  /**
   * Supprime une exigence entre deux pièces.
   * 
   * @param reference la pièce de référence
   * @param target    la pièce dont l'exigence doit être supprimée
   * @throws IllegalArgumentException si reference ou target est null
   */
  void removeRequirement(PartType reference, PartType target);
}
