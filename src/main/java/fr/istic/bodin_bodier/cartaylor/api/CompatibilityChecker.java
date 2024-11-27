package fr.istic.bodin_bodier.cartaylor.api;

import java.util.Set;

/**
 * Interface définissant les opérations de vérification des compatibilités
 * entre les différents types de pièces dans le configurateur.
 * 
 * <p>
 * Cette interface permet de :
 * <ul>
 * <li>Vérifier les incompatibilités entre pièces</li>
 * <li>Vérifier les exigences (dépendances) entre pièces</li>
 * </ul>
 * 
 * @see CompatibilityChecker
 */
public interface CompatibilityChecker {

  /**
   * Retourne l'ensemble des pièces incompatibles avec une pièce de référence.
   * 
   * @param reference la pièce de référence pour laquelle vérifier les
   *                  incompatibilités
   * @return un ensemble des pièces incompatibles avec la référence
   * @throws IllegalArgumentException si la référence est null
   */
  Set<PartType> getIncompatibilities(PartType reference);

  /**
   * Retourne l'ensemble des pièces requises par une pièce de référence.
   * 
   * @param reference la pièce de référence pour laquelle vérifier les exigences
   * @return un ensemble des pièces requises par la référence
   * @throws IllegalArgumentException si la référence est null
   */
  Set<PartType> getRequirements(PartType reference);
}
