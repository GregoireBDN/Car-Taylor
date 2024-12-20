package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.api.Configuration;
import fr.istic.bodin_bodier.cartaylor.api.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test de la classe PrintDescriptionVisitor.
 */
public class PrintDescriptionVisitorTest {

  private ByteArrayOutputStream outputStream;
  private Visitor visitor;

  /**
   * Initialisation des tests.
   */
  @BeforeEach
  public void setUp() {
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    visitor = new PrintDescriptionVisitor(printStream);
  }

  /**
   * Test de la visite d'un type de pièce.
   */
  @Test
  public void testVisitPartType() {
    PartType partType = new PartTypeImpl("V8", new EngineCategory(), PartImpl.class, 10000);
    visitor.visit(partType);
    assertEquals("Category: Engine\nPartType: V8, Price: 10000\n", outputStream.toString());
  }

  /**
   * Test de la visite d'une catégorie.
   */
  @Test
  public void testVisitCategory() {
    Category category = new EngineCategory();
    visitor.visit(category);
    assertEquals("Category: " + category.getName() + "\n", outputStream.toString());
  }

  /**
   * Test de la visite d'une configuration.
   */
  @Test
  public void testVisitConfiguration() {
    Configuration configuration = mock(Configuration.class);
    when(configuration.toString()).thenReturn("Mocked Configuration");
    visitor.visit(configuration);
    assertEquals("Configuration: \n", outputStream.toString());
  }
}