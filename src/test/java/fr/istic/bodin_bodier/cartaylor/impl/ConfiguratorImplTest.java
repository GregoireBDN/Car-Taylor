package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
  @BeforeEach
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
    assertEquals(4, categories.size());
  }

  /**
   * Vérifie que la récupération des types de pièces fonctionne correctement.
   */
  @Test
  public void testGetPartTypes() {
    Set<PartType> partTypes = configurator.getPartTypes();
    assertNotNull(partTypes);
    assertFalse(partTypes.isEmpty());
    assertEquals(4, partTypes.size());
  }

  /**
   * Vérifie que la récupération des variantes pour une catégorie
   * fonctionne correctement.
   */
  @Test
  public void testGetVariants() {
    Category engineCategory = configurator.getCategories().stream()
        .filter(c -> c.getName().equals("Engine"))
        .findFirst()
        .orElse(null);
    assertNotNull(engineCategory);

    Set<PartType> variants = configurator.getVariants(engineCategory);
    assertNotNull(variants);
    assertFalse(variants.isEmpty());
    assertEquals(2, variants.size());
  }

  /**
   * Vérifie que la récupération des variantes avec une catégorie null
   * lève une exception appropriée.
   */
  @Test
  public void testGetVariantsWithNullCategory() {
    assertThrows(IllegalArgumentException.class, () -> {
      configurator.getVariants(null);
    });
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