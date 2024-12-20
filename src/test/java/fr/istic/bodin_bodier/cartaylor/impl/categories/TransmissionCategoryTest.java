package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TransmissionCategoryTest {

  @Test
  public void testDefaultConstructor() {
    TransmissionCategory transmission = new TransmissionCategory();
    assertEquals("Transmission", transmission.getName());
    assertEquals(TransmissionCategory.TransmissionType.MANUAL, transmission.getType());
  }

  @Test
  public void testParameterizedConstructor() {
    TransmissionCategory transmission = new TransmissionCategory(TransmissionCategory.TransmissionType.AUTOMATIC);
    assertEquals("Transmission", transmission.getName());
    assertEquals(TransmissionCategory.TransmissionType.AUTOMATIC, transmission.getType());
  }

  @Test
  public void testEquals() {
    TransmissionCategory transmission1 = new TransmissionCategory(TransmissionCategory.TransmissionType.MANUAL);
    TransmissionCategory transmission2 = new TransmissionCategory(TransmissionCategory.TransmissionType.MANUAL);
    TransmissionCategory transmission3 = new TransmissionCategory(TransmissionCategory.TransmissionType.AUTOMATIC);

    assertTrue(transmission1.equals(transmission2));

    // Teste l'inégalité entre deux objets différents
    assertFalse(transmission1.equals(transmission3));

    // Teste l'égalité avec le même objet
    assertTrue(transmission1.equals(transmission1));

    // Teste l'inégalité avec un objet null
    assertFalse(transmission1.equals(null));

    // Teste l'inégalité avec un objet d'une classe différente
    assertFalse(transmission1.equals("Some String"));
  }
}