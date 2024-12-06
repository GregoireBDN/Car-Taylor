package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.*;

import java.util.*;

/**
 * Implémentation de l'interface Configuration qui gère la sélection
 * des pièces dans le configurateur de voiture.
 * 
 * <p>
 * Cette classe vérifie la validité et l'exhaustivité de la configuration
 * en fonction des règles de compatibilité et des exigences.
 * 
 * @see Configuration
 */
public class ConfigurationImpl implements Configuration {
  private final Map<Category, PartType> selections;
  private final Configurator configurator;

  public ConfigurationImpl(Configurator configurator) {
    if (configurator == null) {
      throw new IllegalArgumentException("Le configurateur ne peut pas être null");
    }
    this.selections = new HashMap<>();
    this.configurator = configurator;
  }

  /**
   * Vérifie si la configuration actuelle est valide en respectant
   * les règles de compatibilité et les exigences.
   * 
   * @return true si la configuration est valide, false sinon
   */
  @Override
  public boolean isValid() {
    for (PartType selectedPart : getSelectedParts()) {
      // Vérifier les incompatibilités
      Set<PartType> incompatibilities = configurator.getCompatibilityChecker().getIncompatibilities(selectedPart);
      if (Collections.disjoint(incompatibilities, getSelectedParts())) {
        continue;
      }
      return false;
    }

    // Vérifier les exigences
    for (PartType selectedPart : getSelectedParts()) {
      Set<PartType> requirements = configurator.getCompatibilityChecker().getRequirements(selectedPart);
      if (!getSelectedParts().containsAll(requirements)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Vérifie si toutes les catégories ont une pièce sélectionnée.
   * 
   * @return true si la configuration est complète, false sinon
   */
  @Override
  public boolean isComplete() {
    for (Category category : configurator.getCategories()) {
      if (!selections.containsKey(category) || selections.get(category) == null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Retourne l'ensemble des pièces actuellement sélectionnées.
   * 
   * @return un ensemble de pièces sélectionnées
   */
  @Override
  public Set<PartType> getSelectedParts() {
    return new HashSet<>(selections.values());
  }

  /**
   * Sélectionne une pièce pour sa catégorie correspondante.
   * 
   * @param chosenPart la pièce à sélectionner
   * @throws IllegalArgumentException si la pièce choisie est null
   */
  @Override
  public void selectPart(PartType chosenPart) {
    if (chosenPart == null) {
      throw new IllegalArgumentException("La pièce choisie ne peut pas être null");
    }
    selections.put(chosenPart.getCategory(), chosenPart);
  }

  /**
   * Retourne la pièce sélectionnée pour une catégorie donnée.
   * 
   * @param category la catégorie pour laquelle obtenir la sélection
   * @return la pièce sélectionnée pour la catégorie, ou null si aucune
   */
  @Override
  public PartType getSelectionForCategory(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("La catégorie ne peut pas être null");
    }
    return selections.get(category);
  }

  /**
   * Désélectionne la pièce pour une catégorie donnée.
   * 
   * @param categoryToClear la catégorie à désélectionner
   * @throws IllegalArgumentException si la catégorie est null
   */
  @Override
  public void unselectPartType(Category categoryToClear) {
    if (categoryToClear == null) {
      throw new IllegalArgumentException("La catégorie ne peut pas être null");
    }
    selections.remove(categoryToClear);
  }

  /**
   * Efface toutes les sélections de pièces.
   */
  @Override
  public void clear() {
    selections.clear();
  }

  /**
   * Retourne le prix total de la configuration.
   * 
   * @return le prix total de la configuration
   */
  @Override
  public int getTotalPrice() {
    return getSelectedParts().stream().mapToInt(PartType::getPrice).sum();
  }
}
