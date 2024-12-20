package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe InteriorCategory.
 */
public class InteriorCategoryTest {

  /**
   * Teste le constructeur par défaut de InteriorCategory.
   */
  @Test
  public void testDefaultConstructor() {
    InteriorCategory interior = new InteriorCategory();
    assertEquals("Interior", interior.getName());
    assertEquals(InteriorCategory.FinishType.STANDARD, interior.getFinishType());
    assertEquals(InteriorCategory.Material.LEATHER, interior.getMaterial());
  }

  /**
   * Teste le constructeur paramétré de InteriorCategory.
   */
  @Test
  public void testParameterizedConstructor() {
    InteriorCategory interior = new InteriorCategory(InteriorCategory.FinishType.HIGH_END,
        InteriorCategory.Material.CLOTH);
    assertEquals("Interior", interior.getName());
    assertEquals(InteriorCategory.FinishType.HIGH_END, interior.getFinishType());
    assertEquals(InteriorCategory.Material.CLOTH, interior.getMaterial());
  }

  /**
   * Teste la méthode equals de InteriorCategory.
   */
  @Test
  public void testEquals() {
    InteriorCategory interior1 = new InteriorCategory(InteriorCategory.FinishType.STANDARD,
        InteriorCategory.Material.LEATHER);
    InteriorCategory interior2 = new InteriorCategory(InteriorCategory.FinishType.STANDARD,
        InteriorCategory.Material.LEATHER);
    InteriorCategory interior3 = new InteriorCategory(InteriorCategory.FinishType.HIGH_END,
        InteriorCategory.Material.CLOTH);

    assertTrue(interior1.equals(interior2));
    assertFalse(interior1.equals(interior3));

    assertFalse(interior1.equals(null)); // Test avec null
    assertFalse(interior1.equals("not an InteriorCategory")); // Test avec un objet d'un type différent

    InteriorCategory interior4 = new InteriorCategory(InteriorCategory.FinishType.STANDARD,
        InteriorCategory.Material.CLOTH);
    assertFalse(interior1.equals(interior4)); // Différent matériau
  }
}