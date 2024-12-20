package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe TransmissionCategory.
 */
public class TransmissionCategoryTest {

  /**
   * Teste le constructeur par défaut de TransmissionCategory.
   */
  @Test
  public void testDefaultConstructor() {
    TransmissionCategory transmission = new TransmissionCategory();
    assertEquals("Transmission", transmission.getName());
    assertEquals(TransmissionCategory.TransmissionType.MANUAL, transmission.getType());
  }

  /**
   * Teste le constructeur paramétré de TransmissionCategory.
   */
  @Test
  public void testParameterizedConstructor() {
    TransmissionCategory transmission = new TransmissionCategory(TransmissionCategory.TransmissionType.AUTOMATIC);
    assertEquals("Transmission", transmission.getName());
    assertEquals(TransmissionCategory.TransmissionType.AUTOMATIC, transmission.getType());
  }

  /**
   * Teste la méthode equals de TransmissionCategory.
   */
  @Test
  public void testEquals() {
    TransmissionCategory transmission1 = new TransmissionCategory(TransmissionCategory.TransmissionType.MANUAL);
    TransmissionCategory transmission2 = new TransmissionCategory(TransmissionCategory.TransmissionType.MANUAL);
    TransmissionCategory transmission3 = new TransmissionCategory(TransmissionCategory.TransmissionType.AUTOMATIC);

    assertTrue(transmission1.equals(transmission2));

    assertFalse(transmission1.equals(transmission3));

    assertTrue(transmission1.equals(transmission1));

    assertFalse(transmission1.equals(null));

    assertFalse(transmission1.equals("Some String"));
  }
}