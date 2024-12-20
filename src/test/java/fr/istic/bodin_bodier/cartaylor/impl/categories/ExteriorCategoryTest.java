package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ExteriorCategoryTest {

  /**
   * Teste le constructeur par défaut de ExteriorCategory.
   */
  @Test
  public void testDefaultConstructor() {
    ExteriorCategory exterior = new ExteriorCategory();
    assertEquals("Exterior", exterior.getName());
    assertEquals(ExteriorCategory.Color.BLACK, exterior.getColor());
    assertEquals(ExteriorCategory.PaintType.CLASSIC, exterior.getPaintType());
  }

  /**
   * Teste la méthode equals de ExteriorCategory.
   */
  @Test
  public void testEquals() {
    ExteriorCategory exterior1 = new ExteriorCategory();
    ExteriorCategory exterior2 = new ExteriorCategory();
    ExteriorCategory exterior3 = new ExteriorCategory();
    exterior3.setColor(ExteriorCategory.Color.RED); // Simule un changement de couleur

    assertTrue(exterior1.equals(exterior2));

    assertFalse(exterior1.equals(exterior3));

    assertTrue(exterior1.equals(exterior1));

    assertFalse(exterior1.equals(null));

    assertFalse(exterior1.equals("Some String"));
  }
}
