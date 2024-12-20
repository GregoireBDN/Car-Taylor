package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.*;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;
import fr.istic.bodin_bodier.cartaylor.api.Catalog;

/**
 * Implémentation de l'interface Configurator qui gère le processus
 * de configuration des voitures, y compris le chargement des données
 * et la vérification des compatibilités.
 * 
 * <p>
 * Cette classe initialise le catalogue de pièces et gère les
 * interactions avec le gestionnaire de compatibilité.
 * 
 * @see Configurator
 */
public class ConfiguratorImpl implements Configurator {
  private final Catalog catalogue;
  private final CompatibilityManager compatibilityManager;
  private final Configuration configuration;
  private final Visitor description;

  /**
   * Constructeur de la classe ConfiguratorImpl.
   * 
   * @param resourcePath le chemin vers le fichier de ressources JSON
   * @throws RuntimeException si le fichier de ressources ne peut pas être chargé
   */
  public ConfiguratorImpl(String resourcePath) {
    this.catalogue = new CatalogImpl();
    this.description = new PrintDescriptionVisitor(System.out);
    try {
      // Charger le catalogue depuis les ressources
      InputStream inputStream = getClass().getClassLoader()
          .getResourceAsStream(resourcePath);
      if (inputStream == null) {
        throw new RuntimeException("Impossible de trouver le fichier: " + resourcePath);
      }
      catalogue.loadFromJSON(inputStream);
    } catch (Exception e) {
      throw new RuntimeException("Erreur lors du chargement du catalogue", e);
    }

    this.compatibilityManager = new CompatibilityManagerImpl();
    this.configuration = new ConfigurationImpl(this);
  }

  /**
   * Retourne l'ensemble des catégories disponibles dans le catalogue.
   * 
   * @return un ensemble de catégories
   */
  @Override
  public Set<Category> getCategories() {
    return catalogue.getCategories();
  }

  /**
   * Retourne l'ensemble des types de pièces disponibles dans le catalogue.
   * 
   * @return un ensemble de types de pièces
   */
  @Override
  public Set<PartType> getPartTypes() {
    return catalogue.getPartTypes();
  }

  /**
   * Retourne les variantes de types de pièces pour une catégorie donnée.
   * 
   * @param category la catégorie pour laquelle obtenir les variantes
   * @return un ensemble de types de pièces pour la catégorie
   * @throws IllegalArgumentException si la catégorie est null
   */
  @Override
  public Set<PartType> getVariants(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("La catégorie ne peut pas être null");
    }
    return catalogue.getPartTypes().stream()
        .filter(partType -> partType.getCategory().equals(category))
        .collect(Collectors.toSet());
  }

  /**
   * Retourne la configuration actuelle.
   * 
   * @return la configuration
   */
  @Override
  public Configuration getConfiguration() {
    return configuration;
  }

  /**
   * Retourne le gestionnaire de compatibilité utilisé pour vérifier
   * les règles de compatibilité entre les pièces.
   * 
   * @return le gestionnaire de compatibilité
   */
  @Override
  public CompatibilityChecker getCompatibilityChecker() {
    return compatibilityManager;
  }

  /**
   * Imprime la description de la configuration actuelle.
   * 
   * @param stream le flux de sortie
   */
  @Override
  public void printDescription() {
    description.visit(configuration);
  }
}
