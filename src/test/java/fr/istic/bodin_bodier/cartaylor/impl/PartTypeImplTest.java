package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe PartTypeImpl.
 * 
 * <p>
 * Ces tests vérifient la création et le comportement des types de pièces
 * dans différentes situations (paramètres valides et invalides).
 *
 */
public class PartTypeImplTest {
  private Category engineCategory;

  /**
   * Initialise la catégorie de test avant chaque test.
   */
  @BeforeEach
  public void setUp() {
    engineCategory = new EngineCategory();
  }

  /**
   * Vérifie qu'un type de pièce peut être créé avec des paramètres valides.
   */
  @Test
  public void testValidPartType() {
    PartTypeImpl partType = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
    assertEquals("V8", partType.getName());
    assertEquals(engineCategory, partType.getCategory());
    assertEquals(10000, partType.getPrice());
  }

  /**
   * Vérifie qu'une exception est levée lors de la création
   * d'un type de pièce avec un nom null.
   */
  @Test
  public void testNullName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PartTypeImpl(null, engineCategory, PartImpl.class, 10000);
    });
  }

  /**
   * Vérifie qu'une exception est levée lors de la création
   * d'un type de pièce avec une catégorie null.
   */
  @Test
  public void testNullCategory() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PartTypeImpl("V8", null, PartImpl.class, 10000);
    });
  }

  /**
   * Vérifie que la comparaison entre types de pièces fonctionne correctement.
   */
  @Test
  public void testEquals() {
    PartTypeImpl partType1 = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
    PartTypeImpl partType2 = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
    PartTypeImpl partType3 = new PartTypeImpl("V6", engineCategory, PartImpl.class, 10000);

    assertTrue(partType1.equals(partType2));
    assertFalse(partType1.equals(partType3));
  }
}