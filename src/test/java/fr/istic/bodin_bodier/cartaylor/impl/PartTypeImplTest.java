package fr.istic.bodin_bodier.cartaylor.impl;

import static org.junit.jupiter.api.Assertions.*;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartTypeImplTest {

  private PartType partType;
  private Category engineCategory;

  @BeforeEach
  public void setUp() {
    engineCategory = new EngineCategory();
    partType = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
  }

  @Test
  public void testGetName() {
    assertEquals("V8", partType.getName());
  }

  @Test
  public void testGetPrice() {
    assertEquals(10000, partType.getPrice());
  }

  @Test
  public void testGetCategory() {
    assertEquals(engineCategory, partType.getCategory());
  }

  @Test
  public void testEqualsAndHashCode() {
    PartType samePartType = new PartTypeImpl("V8", engineCategory, PartImpl.class, 10000);
    PartType differentPartType = new PartTypeImpl("V6", engineCategory, PartImpl.class, 8000);

    assertEquals(partType, samePartType);
    assertNotEquals(partType, differentPartType);
    assertEquals(partType.hashCode(), samePartType.hashCode());
    assertNotEquals(partType.hashCode(), differentPartType.hashCode());
  }

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
}