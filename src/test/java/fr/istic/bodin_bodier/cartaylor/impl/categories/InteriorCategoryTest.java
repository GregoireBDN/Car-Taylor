package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InteriorCategoryTest {

  @Test
  public void testDefaultConstructor() {
    InteriorCategory interior = new InteriorCategory();
    assertEquals("Interior", interior.getName());
    assertEquals(InteriorCategory.FinishType.STANDARD, interior.getFinishType());
    assertEquals(InteriorCategory.Material.LEATHER, interior.getMaterial());
  }

  @Test
  public void testParameterizedConstructor() {
    InteriorCategory interior = new InteriorCategory(InteriorCategory.FinishType.HIGH_END,
        InteriorCategory.Material.CLOTH);
    assertEquals("Interior", interior.getName());
    assertEquals(InteriorCategory.FinishType.HIGH_END, interior.getFinishType());
    assertEquals(InteriorCategory.Material.CLOTH, interior.getMaterial());
  }

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
  }
}