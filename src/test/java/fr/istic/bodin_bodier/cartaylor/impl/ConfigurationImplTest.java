package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import fr.istic.bodin_bodier.cartaylor.impl.PartImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests unitaires pour la classe ConfigurationImpl.
 * 
 * <p>
 * Ces tests vérifient la gestion des configurations de véhicules,
 * notamment la sélection des pièces, la validation et la réinitialisation
 * de la configuration.
 *
 */
public class ConfigurationImplTest {
  private Configurator configurator;
  private Configuration configuration;
  private Category engineCategory;
  private PartType enginePart;
  private CompatibilityChecker compatibilityChecker;

  /**
   * Initialise l'environnement de test avec des mocks et des données de test.
   */
  @Before
  public void setUp() {
    configurator = mock(Configurator.class);
    compatibilityChecker = mock(CompatibilityChecker.class);

    Set<Category> categories = new HashSet<>();
    engineCategory = new CategoryImpl("Engine");
    categories.add(engineCategory);
    when(configurator.getCategories()).thenReturn(categories);
    when(configurator.getCompatibilityChecker()).thenReturn(compatibilityChecker);

    configuration = new ConfigurationImpl(configurator);

    enginePart = new PartTypeImpl("V8", engineCategory, PartImpl.class);
  }

  /**
   * Vérifie que la sélection d'une pièce fonctionne correctement.
   */
  @Test
  public void testSelectPart() {
    configuration.selectPart(enginePart);
    assertEquals(enginePart, configuration.getSelectionForCategory(engineCategory));
  }

  /**
   * Vérifie que la méthode isComplete retourne la bonne valeur
   * selon l'état de la configuration.
   */
  @Test
  public void testIsComplete() {
    assertFalse(configuration.isComplete());
    configuration.selectPart(enginePart);
    assertTrue(configuration.isComplete());
  }

  /**
   * Vérifie que la réinitialisation de la configuration fonctionne correctement.
   */
  @Test
  public void testClear() {
    configuration.selectPart(enginePart);
    configuration.clear();
    assertTrue(configuration.getSelectedParts().isEmpty());
  }

  /**
   * Vérifie que la validation de la configuration fonctionne correctement
   * en tenant compte des incompatibilités.
   */
  @Test
  public void testIsValid() {
    // Setup des incompatibilités
    Set<PartType> incompatibilities = new HashSet<>();
    when(compatibilityChecker.getIncompatibilities(enginePart)).thenReturn(incompatibilities);
    when(compatibilityChecker.getRequirements(enginePart)).thenReturn(new HashSet<>());

    configuration.selectPart(enginePart);
    assertTrue(configuration.isValid());
  }
}