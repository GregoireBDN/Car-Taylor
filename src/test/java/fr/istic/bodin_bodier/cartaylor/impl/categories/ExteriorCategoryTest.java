package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ExteriorCategoryTest {

  @Test
  public void testDefaultConstructor() {
    ExteriorCategory exterior = new ExteriorCategory();
    assertEquals("Exterior", exterior.getName());
    assertEquals(ExteriorCategory.Color.BLACK, exterior.getColor());
    assertEquals(ExteriorCategory.PaintType.CLASSIC, exterior.getPaintType());
  }

  @Test
  public void testEquals() {
    ExteriorCategory exterior1 = new ExteriorCategory();
    ExteriorCategory exterior2 = new ExteriorCategory();
    ExteriorCategory exterior3 = new ExteriorCategory();
    exterior3.setColor(ExteriorCategory.Color.RED); // Simule un changement de couleur

    // Teste l'égalité entre deux objets identiques
    assertTrue(exterior1.equals(exterior2));

    // Teste l'inégalité entre deux objets différents
    assertFalse(exterior1.equals(exterior3));

    // Teste l'égalité avec le même objet
    assertTrue(exterior1.equals(exterior1));

    // Teste l'inégalité avec un objet null
    assertFalse(exterior1.equals(null));

    // Teste l'inégalité avec un objet d'une classe différente
    assertFalse(exterior1.equals("Some String"));
  }
}
