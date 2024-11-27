package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.CompatibilityManager;
import fr.istic.bodin_bodier.cartaylor.api.PartType;

import java.util.*;

/**
 * Implémentation de l'interface CompatibilityManager qui gère les
 * incompatibilités et les exigences entre les différents types de pièces
 * dans le configurateur de voiture.
 * 
 * <p>
 * Cette classe permet d'ajouter, de supprimer et de récupérer les
 * règles de compatibilité entre les pièces.
 * 
 * @see CompatibilityManager
 */
public class CompatibilityManagerImpl implements CompatibilityManager {

  // Maps pour stocker les incompatibilités et les exigences
  private final Map<PartType, Set<PartType>> incompatibilities;

  private final Map<PartType, Set<PartType>> requirements;

  public CompatibilityManagerImpl() {
    this.incompatibilities = new HashMap<>();
    this.requirements = new HashMap<>();
  }

  /**
   * Retourne l'ensemble des incompatibilités pour une pièce donnée.
   * 
   * @param reference la pièce de référence
   * @return un ensemble non modifiable des pièces incompatibles avec la référence
   * @throws IllegalArgumentException si la référence est null
   */
  @Override
  public Set<PartType> getIncompatibilities(PartType reference) {
    if (reference == null) {
      throw new IllegalArgumentException("La référence ne peut pas être null");
    }
    return Collections.unmodifiableSet(
        incompatibilities.getOrDefault(reference, new HashSet<>()));
  }

  /**
   * Retourne l'ensemble des exigences pour une pièce donnée.
   * 
   * @param reference la pièce de référence
   * @return un ensemble non modifiable des pièces requises par la référence
   * @throws IllegalArgumentException si la référence est null
   */
  @Override
  public Set<PartType> getRequirements(PartType reference) {
    if (reference == null) {
      throw new IllegalArgumentException("La référence ne peut pas être null");
    }
    return Collections.unmodifiableSet(
        requirements.getOrDefault(reference, new HashSet<>()));
  }

  /**
   * Ajoute des incompatibilités entre une pièce de référence et un ensemble de
   * pièces cibles.
   * 
   * @param reference la pièce de référence
   * @param target    l'ensemble des pièces incompatibles avec la référence
   * @throws IllegalArgumentException si reference ou target est null, ou si
   *                                  target contient des éléments null
   */
  @Override
  public void addIncompatibilities(PartType reference, Set<PartType> target) {
    validateParameters(reference, target);

    // Créer ou obtenir l'ensemble des incompatibilités pour la référence
    Set<PartType> incompatibleParts = incompatibilities.computeIfAbsent(
        reference, k -> new HashSet<>());
    incompatibleParts.addAll(target);

    // Ajouter les incompatibilités bidirectionnelles
    for (PartType targetPart : target) {
      Set<PartType> reverseIncompatibilities = incompatibilities.computeIfAbsent(
          targetPart, k -> new HashSet<>());
      reverseIncompatibilities.add(reference);
    }
  }

  /**
   * Supprime une incompatibilité entre deux pièces.
   * 
   * @param reference la pièce de référence
   * @param target    la pièce dont l'incompatibilité doit être supprimée
   * @throws IllegalArgumentException si reference ou target est null
   */
  @Override
  public void removeIncompatibility(PartType reference, PartType target) {
    if (reference == null || target == null) {
      throw new IllegalArgumentException("Les paramètres ne peuvent pas être null");
    }
    Set<PartType> incompatibleParts = incompatibilities.get(reference);
    if (incompatibleParts != null) {
      incompatibleParts.remove(target);
    }
    Set<PartType> reverseIncompatibilities = incompatibilities.get(target);
    if (reverseIncompatibilities != null) {
      reverseIncompatibilities.remove(reference);
    }
  }

  /**
   * Ajoute des exigences entre une pièce de référence et un ensemble de pièces
   * requises.
   * 
   * @param reference la pièce de référence
   * @param target    l'ensemble des pièces requises par la référence
   * @throws IllegalArgumentException si reference ou target est null, ou si
   *                                  target contient des éléments null
   */
  @Override
  public void addRequirements(PartType reference, Set<PartType> target) {
    validateParameters(reference, target);

    Set<PartType> requiredParts = requirements.computeIfAbsent(
        reference, k -> new HashSet<>());
    requiredParts.addAll(target);
  }

  /**
   * Supprime une exigence entre deux pièces.
   * 
   * @param reference la pièce de référence
   * @param target    la pièce dont l'exigence doit être supprimée
   * @throws IllegalArgumentException si reference ou target est null
   */
  @Override
  public void removeRequirement(PartType reference, PartType target) {
    if (reference == null || target == null) {
      throw new IllegalArgumentException("Les paramètres ne peuvent pas être null");
    }

    Set<PartType> requiredParts = requirements.get(reference);
    if (requiredParts != null) {
      requiredParts.remove(target);
    }
  }

  /**
   * Valide les paramètres pour s'assurer qu'ils ne sont pas null et que
   * l'ensemble cible ne contient pas d'éléments null.
   * 
   * @param reference la pièce de référence
   * @param target    l'ensemble des pièces à valider
   * @throws IllegalArgumentException si reference ou target est null, ou si
   *                                  target contient des éléments null
   */
  private void validateParameters(PartType reference, Set<PartType> target) {
    if (reference == null || target == null) {
      throw new IllegalArgumentException("Les paramètres ne peuvent pas être null");
    }
    for (PartType part : target) {
      if (part == null) {
        throw new IllegalArgumentException("Les éléments de la cible ne peuvent pas être null");
      }
    }
  }
}