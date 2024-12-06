package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import fr.istic.bodin_bodier.cartaylor.impl.PartImpl;

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
  @Before
  public void setUp() {
    engineCategory = new CategoryImpl("Engine");
  }

  /**
   * Vérifie qu'un type de pièce peut être créé avec des paramètres valides.
   */
  @Test
  public void testValidPartType() {
    PartTypeImpl partType = new PartTypeImpl("V8", engineCategory, PartImpl.class);
    assertEquals("V8", partType.getName());
    assertEquals(engineCategory, partType.getCategory());
  }

  /**
   * Vérifie qu'une exception est levée lors de la création
   * d'un type de pièce avec un nom null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    new PartTypeImpl(null, engineCategory, PartImpl.class);
  }

  /**
   * Vérifie qu'une exception est levée lors de la création
   * d'un type de pièce avec une catégorie null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullCategory() {
    new PartTypeImpl("V8", null, PartImpl.class);
  }

  /**
   * Vérifie que la comparaison entre types de pièces fonctionne correctement.
   */
  @Test
  public void testEquals() {
    PartTypeImpl partType1 = new PartTypeImpl("V8", engineCategory, PartImpl.class);
    PartTypeImpl partType2 = new PartTypeImpl("V8", engineCategory, PartImpl.class);
    PartTypeImpl partType3 = new PartTypeImpl("V6", engineCategory, PartImpl.class);

    assertTrue(partType1.equals(partType2));
    assertFalse(partType1.equals(partType3));
  }
}