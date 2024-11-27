package fr.istic.bodin_bodier.cartaylor.impl;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests unitaires pour la classe CategoryImpl.
 * 
 * <p>
 * Ces tests vérifient la création de catégories et leurs comportements
 * dans différentes situations (nom valide, null, vide).
 */
public class CategoryImplTest {

  /**
   * Vérifie qu'une catégorie peut être créée avec un nom valide.
   */
  @Test
  public void testValidCategory() {
    CategoryImpl category = new CategoryImpl("Engine");
    assertEquals("Engine", category.getName());
  }

  /**
   * Vérifie qu'une exception est levée lors de la création
   * d'une catégorie avec un nom null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    new CategoryImpl(null);
  }

  /**
   * Vérifie qu'une exception est levée lors de la création
   * d'une catégorie avec un nom vide.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    new CategoryImpl("");
  }

  /**
   * Vérifie que la comparaison entre catégories fonctionne correctement.
   */
  @Test
  public void testEquals() {
    CategoryImpl category1 = new CategoryImpl("Engine");
    CategoryImpl category2 = new CategoryImpl("Engine");
    CategoryImpl category3 = new CategoryImpl("Transmission");

    assertTrue(category1.equals(category2));
    assertFalse(category1.equals(category3));
  }
}