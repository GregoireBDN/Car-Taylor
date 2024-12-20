package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

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
  @BeforeEach
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
      Set<PartType> partTypes = catalogue.getPartTypes();
      assertFalse(partTypes.isEmpty(), "Le catalogue devrait contenir des types de pièces");

      // Vérification des catégories spécifiques
      Set<Category> categories = catalogue.getCategories();
      assertFalse(categories.isEmpty(), "Le catalogue devrait contenir des catégories");
      assertEquals(4, categories.size());
      assertTrue(categories.contains(catalogue.getCategory("Engine")));
      assertTrue(categories.contains(catalogue.getCategory("Transmission")));
      assertTrue(categories.contains(catalogue.getCategory("Exterior")));
      assertTrue(categories.contains(catalogue.getCategory("Interior")));
    }
  }

  /**
   * Vérifie que l'exception IOException est lancée lorsque le chemin est
   * invalide.
   */
  @Test
  public void testLoadFromInvalidPath() {
    assertThrows(IOException.class, () -> {
      catalogue.loadFromJSON("invalid/path.json");
    });
  }

  /**
   * Vérifie que les catégories sont initialisées correctement.
   */
  @Test
  public void testInitialization() {
    Set<Category> categories = catalogue.getCategories();
    assertEquals(4, categories.size());
    assertNotNull(catalogue.getCategory("Engine"));
    assertNotNull(catalogue.getCategory("Transmission"));
    assertNotNull(catalogue.getCategory("Exterior"));
    assertNotNull(catalogue.getCategory("Interior"));
  }

  /**
   * Vérifie que le chargement d'un fichier JSON valide fonctionne correctement.
   * 
   * @throws IOException
   */
  @Test
  public void testLoadFromJSONValid() throws IOException {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/test-catalogue.json")) {
      assertNotNull(inputStream, "Le fichier JSON de test doit être présent");
      catalogue.loadFromJSON(inputStream);
      Set<PartType> partTypes = catalogue.getPartTypes();
      assertNotNull(partTypes, "Les types de pièces devraient être chargés");
    }
  }

  /**
   * Vérifie que les types de pièces pour une catégorie spécifique sont
   * correctement chargés.
   * 
   * @throws IOException
   */
  @Test
  public void testGetPartTypesForCategory() throws IOException {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/test-catalogue.json")) {
      assertNotNull(inputStream, "Le fichier JSON de test doit être présent");
      catalogue.loadFromJSON(inputStream);
      Category engineCategory = catalogue.getCategory("Engine");
      Set<PartType> enginePartTypes = catalogue.getPartTypesForCategory(engineCategory);
      assertNotNull(enginePartTypes, "Les types de pièces pour la catégorie 'Engine' devraient être chargés");
      assertFalse(enginePartTypes.isEmpty(), "La catégorie 'Engine' devrait contenir des types de pièces");
    }
  }

  /**
   * Vérifie que l'exception IllegalStateException est lancée lorsque la catégorie
   * n'existe pas.
   */
  @Test
  public void testLoadFromJSONWithInvalidCategory() {
    String invalidJson = "{ \"partTypes\": [{ \"name\": \"InvalidPart\", \"category\": \"NonExistent\", \"price\": 100 }] }";
    InputStream inputStream = new ByteArrayInputStream(invalidJson.getBytes());

    assertThrows(IllegalStateException.class, () -> {
      catalogue.loadFromJSON(inputStream);
    }, "Une exception devrait être lancée pour une catégorie inexistante");
  }
}