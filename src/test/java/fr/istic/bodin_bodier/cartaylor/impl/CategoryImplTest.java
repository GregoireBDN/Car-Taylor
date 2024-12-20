package fr.istic.bodin_bodier.cartaylor.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;
import fr.istic.bodin_bodier.cartaylor.impl.categories.TransmissionCategory;
import fr.istic.bodin_bodier.cartaylor.api.Category;

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
    Category category = new EngineCategory();
    assertEquals("Engine", category.getName());
  }

  /**
   * Vérifie que la comparaison entre catégories fonctionne correctement.
   */
  @Test
  public void testEquals() {
    Category category1 = new EngineCategory();
    Category category2 = new EngineCategory();
    Category category3 = new TransmissionCategory();

    assertTrue(category1.equals(category2));
    assertFalse(category1.equals(category3));
  }
}