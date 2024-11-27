package fr.istic.bodin_bodier.cartaylor.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Tests unitaires pour la classe Catalogue.
 * 
 * <p>
 * Ces tests vérifient le chargement des données depuis un fichier JSON
 * et la gestion des catégories et types de pièces.
 */
public class CatalogueTest {
  private Catalogue catalogue;

  /**
   * Initialise un nouveau catalogue avant chaque test.
   */
  @Before
  public void setUp() {
    catalogue = new Catalogue();
  }

  /**
   * Vérifie que le chargement d'un fichier JSON valide fonctionne correctement.
   * Le test s'assure que le catalogue contient des catégories et des types de
   * pièces.
   * 
   * @throws IOException
   */
  @Test
  public void testLoadFromJSON() throws IOException {
    try (InputStream inputStream = getClass().getClassLoader()
        .getResourceAsStream("data/test-catalogue.json")) {

      if (inputStream == null) {
        throw new RuntimeException("Fichier test-catalogue.json non trouvé dans les ressources");
      }

      catalogue.loadFromJSON(inputStream);

      assertFalse("Le catalogue devrait contenir des catégories",
          catalogue.getCategories().isEmpty());
      assertFalse("Le catalogue devrait contenir des types de pièces",
          catalogue.getPartTypes().isEmpty());
    }
  }

  /**
   * Test the loadFromJSON method with an invalid path
   * 
   * @throws IOException
   */
  @Test(expected = IOException.class)
  public void testLoadFromInvalidPath() throws IOException {
    catalogue.loadFromJSON("invalid/path.json");
  }
}