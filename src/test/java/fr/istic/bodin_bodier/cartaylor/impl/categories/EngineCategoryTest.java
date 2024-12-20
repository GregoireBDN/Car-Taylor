package fr.istic.bodin_bodier.cartaylor.impl.categories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe EngineCategory.
 */
public class EngineCategoryTest {

  /**
   * Teste le constructeur par défaut de EngineCategory.
   */
  @Test
  public void testDefaultConstructor() {
    EngineCategory engine = new EngineCategory();
    assertEquals("Engine", engine.getName());
    assertEquals(80, engine.getPower());
    assertEquals(EngineCategory.FuelType.Diesel, engine.getFuelType());
  }

  /**
   * Teste les méthodes setters de EngineCategory.
   */
  @Test
  public void testSetters() {
    EngineCategory engine = new EngineCategory();
    engine.setPower(100);
    engine.setFuelType(EngineCategory.FuelType.Gasoline);

    assertEquals(100, engine.getPower());
    assertEquals(EngineCategory.FuelType.Gasoline, engine.getFuelType());
  }

  /**
   * Teste la méthode equals de EngineCategory.
   */
  @Test
  public void testEquals() {
    EngineCategory engine1 = new EngineCategory();
    EngineCategory engine2 = new EngineCategory();
    EngineCategory engine3 = new EngineCategory();
    engine3.setFuelType(EngineCategory.FuelType.Gasoline); // Simule un changement de type de carburant

    // Teste l'égalité entre deux objets identiques
    assertTrue(engine1.equals(engine2));

    // Teste l'inégalité entre deux objets différents
    assertFalse(engine1.equals(engine3));

    // Teste l'égalité avec le même objet
    assertTrue(engine1.equals(engine1));

    // Teste l'inégalité avec un objet null
    assertFalse(engine1.equals(null));

    // Teste l'inégalité avec un objet d'une classe différente
    assertFalse(engine1.equals("Some String"));
  }
}