package fr.istic.bodin_bodier.cartaylor.impl;

import static org.junit.jupiter.api.Assertions.*;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe PartTypeImpl.
 */
public class PartTypeImplTest {

  private PartType partType;
  private Category engineCategory;

  /**
   * Initialise les objets nécessaires pour les tests.
   */
  @BeforeEach
  public void setUp() {
    engineCategory = new EngineCategory();
    partType = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
  }

  /**
   * Teste la méthode getName de PartTypeImpl.
   */
  @Test
  public void testGetName() {
    assertEquals("V8", partType.getName());
  }

  /**
   * Teste la méthode getPrice de PartTypeImpl.
   */
  @Test
  public void testGetPrice() {
    assertEquals(10000, partType.getPrice());
  }

  /**
   * Teste la méthode getCategory de PartTypeImpl.
   */
  @Test
  public void testGetCategory() {
    assertEquals(engineCategory, partType.getCategory());
  }

  /**
   * Teste les méthodes equals et hashCode de PartTypeImpl.
   */
  @Test
  public void testEqualsAndHashCode() {
    PartType samePartType = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
    PartType differentPartType = new PartTypeImpl("V6", engineCategory, PartImpl.class, 8000);

    assertEquals(partType, samePartType);
    assertNotEquals(partType, differentPartType);
    assertEquals(partType.hashCode(), samePartType.hashCode());
    assertNotEquals(partType.hashCode(), differentPartType.hashCode());
  }

  /**
   * Teste les paramètres invalides pour PartTypeImpl.
   */
  @Test
  public void testInvalidParameters() {
    assertThrows(IllegalArgumentException.class, () -> {
      new PartTypeImpl(null, engineCategory, PartImpl.class, 10000);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new PartTypeImpl("V8", null, PartImpl.class, 10000);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      new PartTypeImpl("V8", engineCategory, PartImpl.class, -100);
    });
  }

  /**
   * Teste la méthode newInstance de PartTypeImpl.
   */
  @Test
  public void testNewInstance() {
    // Teste la création d'une nouvelle instance de PartImpl
    PartImpl partInstance = partType.newInstance();
    assertNotNull(partInstance, "La méthode newInstance devrait retourner une instance non nulle de PartImpl");
    assertEquals(PartImpl.class, partInstance.getClass(), "L'instance créée devrait être de type PartImpl");
  }

  /**
   * Teste la méthode newInstance avec un constructeur invalide.
   */
  @Test
  public void testNewInstanceWithInvalidConstructor() {
    class InvalidPartImpl extends PartImpl {
    }

    PartTypeImpl invalidPartType = new PartTypeImpl("Invalid", engineCategory, InvalidPartImpl.class, 5000);

    assertThrows(RuntimeException.class, () -> {
      invalidPartType.newInstance();
    }, "Une exception devrait être lancée si le constructeur par défaut n'est pas disponible");
  }
}