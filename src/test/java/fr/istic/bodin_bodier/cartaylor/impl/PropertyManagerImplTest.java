package fr.istic.bodin_bodier.cartaylor.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe PropertyManagerImpl.
 */
public class PropertyManagerImplTest {

  private PropertyManagerImpl propertyManager;

  /**
   * Initialisation des tests.
   */
  @BeforeEach
  public void setUp() {
    propertyManager = new PropertyManagerImpl();
  }

  /**
   * Test de l'ajout et de la récupération d'une propriété.
   */
  @Test
  public void testAddAndGetProperty() {
    Set<String> possibleValues = new HashSet<>();
    possibleValues.add("Value1");
    possibleValues.add("Value2");

    propertyManager.addProperty("Property1", possibleValues);
    propertyManager.setProperty("Property1", "Value1");

    Optional<String> value = propertyManager.getProperty("Property1");
    assertTrue(value.isPresent());
    assertEquals("Value1", value.get());
  }

  /**
   * Test de la récupération des noms des propriétés.
   */
  @Test
  public void testGetPropertyNames() {
    propertyManager.addProperty("Property1", Set.of("Value1"));
    propertyManager.addProperty("Property2", Set.of("Value2"));

    Set<String> propertyNames = propertyManager.getPropertyNames();
    assertEquals(2, propertyNames.size());
    assertTrue(propertyNames.contains("Property1"));
    assertTrue(propertyNames.contains("Property2"));
  }

  /**
   * Test de la récupération des valeurs possibles pour une propriété.
   */
  @Test
  public void testGetAvailablePropertyValues() {
    Set<String> possibleValues = Set.of("Value1", "Value2");
    propertyManager.addProperty("Property1", possibleValues);

    Set<String> availableValues = propertyManager.getAvailablePropertyValues("Property1");
    assertEquals(2, availableValues.size());
    assertTrue(availableValues.contains("Value1"));
    assertTrue(availableValues.contains("Value2"));
  }

  /**
   * Test de la définition d'une valeur de propriété invalide.
   */
  @Test
  public void testSetPropertyWithInvalidValue() {
    propertyManager.addProperty("Property1", Set.of("Value1"));

    assertThrows(IllegalArgumentException.class, () -> {
      propertyManager.setProperty("Property1", "InvalidValue");
    });
  }

  /**
   * Test de la définition d'une valeur de propriété invalide.
   */
  @Test
  public void testSetPropertyWithInvalidName() {
    assertThrows(IllegalArgumentException.class, () -> {
      propertyManager.setProperty("InvalidProperty", "Value1");
    });
  }
}