package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe PartImpl.
 */
public class PartImplTest {

  private PartImpl part;
  private PartType partType;
  private Category category;

  /**
   * Initialise les objets nécessaires pour les tests.
   */
  @BeforeEach
  public void setUp() {
    category = new EngineCategory();
    partType = new PartTypeImpl("V8", category, PartImpl.class, 10000);
    part = new PartImpl(partType);
  }

  /**
   * Teste la méthode getName.
   */
  @Test
  public void testGetName() {
    assertEquals("fr.istic.bodin_bodier.cartaylor.impl.PartImpl", part.getName());
  }

  /**
   * Teste la méthode getCategory.
   */
  @Test
  public void testGetCategory() {
    assertEquals(category, part.getCategory());
  }

  /**
   * Teste la méthode getType.
   */
  @Test
  public void testGetType() {
    assertEquals(partType, part.getType());
  }

  /**
   * Teste la méthode getPropertyNames.
   */
  @Test
  public void testGetPropertyNames() {
    part.addProperty("color", () -> "red", value -> {
    }, Set.of("red", "blue", "green"));
    Set<String> propertyNames = part.getPropertyNames();
    assertTrue(propertyNames.contains("color"));
  }

  /**
   * Teste la méthode getProperty.
   */
  @Test
  public void testGetProperty() {
    part.addProperty("color", () -> "red", value -> {
    }, Set.of("red", "blue", "green"));
    Optional<String> color = part.getProperty("color");
    assertTrue(color.isPresent());
    assertEquals("red", color.get());
  }

  /**
   * Teste la méthode setProperty.
   */
  @Test
  public void testSetProperty() {
    final String[] colorValue = { "red" };
    part.addProperty("color", () -> colorValue[0], value -> colorValue[0] = value, Set.of("red", "blue", "green"));
    part.setProperty("color", "blue");
    Optional<String> color = part.getProperty("color");
    assertEquals("blue", color.get());
  }

  /**
   * Teste la méthode getAvailablePropertyValues.
   */
  @Test
  public void testGetAvailablePropertyValues() {
    part.addProperty("color", () -> "red", value -> {
    }, Set.of("red", "blue", "green"));
    Set<String> availableValues = part.getAvailablePropertyValues("color");
    assertTrue(availableValues.contains("red"));
    assertTrue(availableValues.contains("blue"));
    assertTrue(availableValues.contains("green"));
  }

  /**
   * Teste la méthode setProperty avec une valeur invalide.
   */
  @Test
  public void testSetPropertyWithInvalidValue() {
    part.addProperty("color", () -> "red", value -> {
    }, Set.of("red", "blue", "green"));
    assertThrows(IllegalArgumentException.class, () -> {
      part.setProperty("color", "yellow");
    });
  }

  /**
   * Teste la méthode getProperty avec une propriété non existante.
   */
  @Test
  public void testGetPropertyNonExistent() {
    Optional<String> nonExistentProperty = part.getProperty("nonExistent");
    assertTrue(nonExistentProperty.isEmpty(), "La propriété devrait être absente");
  }

  /**
   * Teste la méthode setProperty avec un nom de propriété invalide.
   */
  @Test
  public void testSetPropertyWithInvalidName() {
    assertThrows(IllegalArgumentException.class, () -> {
      part.setProperty("invalidName", "blue");
    }, "Une exception devrait être lancée pour un nom de propriété invalide");
  }
}