package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.*;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
  @BeforeEach
  public void setUp() {
    configurator = mock(Configurator.class);
    compatibilityChecker = mock(CompatibilityChecker.class);

    Set<Category> categories = new HashSet<>();
    engineCategory = new EngineCategory();
    categories.add(engineCategory);
    when(configurator.getCategories()).thenReturn(categories);
    when(configurator.getCompatibilityChecker()).thenReturn(compatibilityChecker);

    configuration = new ConfigurationImpl(configurator);

    enginePart = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
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

  @Test
  public void testHtmlDescription() {
    configuration.selectPart(enginePart);
    assertEquals(
        "<div class='configuration'><h3>Configuration de la voiture</h3><ul><li><strong>Engine:</strong> V8<br>Prix: 10000 €</li><li>Prix total: 10000 €</li></ul></div>",
        configuration.getHtmlDescription());
  }
}
