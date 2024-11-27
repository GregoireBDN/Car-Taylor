package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

/**
 * Tests unitaires pour la classe ConfiguratorImpl.
 * 
 * <p>
 * Ces tests vérifient les fonctionnalités principales du configurateur,
 * notamment l'accès aux catégories, aux variantes et aux composants
 * de configuration.
 *
 */
public class ConfiguratorImplTest {
  private Configurator configurator;

  /**
   * Initialise le configurateur avec des données de test.
   */
  @Before
  public void setUp() {
    configurator = new ConfiguratorImpl("data/test-catalogue.json");
  }

  /**
   * Vérifie que la récupération des catégories fonctionne correctement.
   */
  @Test
  public void testGetCategories() {
    Set<Category> categories = configurator.getCategories();
    assertNotNull(categories);
    assertFalse(categories.isEmpty());
  }

  /**
   * Vérifie que la récupération des variantes pour une catégorie
   * fonctionne correctement.
   */
  @Test
  public void testGetVariants() {
    Category engineCategory = new CategoryImpl("Engine");
    Set<PartType> variants = configurator.getVariants(engineCategory);
    assertNotNull(variants);
    assertFalse(variants.isEmpty());
  }

  /**
   * Vérifie que l'accès à la configuration fonctionne correctement.
   */
  @Test
  public void testGetConfiguration() {
    Configuration configuration = configurator.getConfiguration();
    assertNotNull(configuration);
  }

  /**
   * Vérifie que l'accès au vérificateur de compatibilité
   * fonctionne correctement.
   */
  @Test
  public void testGetCompatibilityChecker() {
    CompatibilityChecker checker = configurator.getCompatibilityChecker();
    assertNotNull(checker);
  }
}