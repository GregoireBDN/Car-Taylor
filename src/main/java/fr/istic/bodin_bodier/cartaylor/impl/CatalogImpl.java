package fr.istic.bodin_bodier.cartaylor.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.api.Catalog;
import fr.istic.bodin_bodier.cartaylor.impl.categories.ExteriorCategory;
import fr.istic.bodin_bodier.cartaylor.impl.categories.InteriorCategory;
import fr.istic.bodin_bodier.cartaylor.impl.categories.TransmissionCategory;
import fr.istic.bodin_bodier.cartaylor.impl.categories.EngineCategory;

/**
 * Classe gérant le catalogue des pièces et catégories disponibles
 * dans le configurateur de voiture.
 * 
 * <p>
 * Cette classe permet le chargement des données depuis un fichier JSON
 * et maintient la cohérence entre les catégories et les types de pièces.
 * 
 * @see Catalog
 */
public class CatalogImpl implements Catalog {

  /** Map associant les noms des catégories à leurs instances */
  private final Map<String, Category> categories;
  /** Ensemble des types de pièces disponibles */
  private final Set<PartType> partTypes;

  public CatalogImpl() {
    this.categories = new HashMap<>();
    this.partTypes = new HashSet<>();
    initializeCategories();
  }

  private void initializeCategories() {
    Category engine = new EngineCategory();
    Category transmission = new TransmissionCategory();
    Category exterior = new ExteriorCategory();
    Category interior = new InteriorCategory();

    categories.put(engine.getName(), engine);
    categories.put(transmission.getName(), transmission);
    categories.put(exterior.getName(), exterior);
    categories.put(interior.getName(), interior);
  }

  /**
   * Charge les données du catalogue depuis un fichier JSON.
   * 
   * @param filePath le chemin vers le fichier JSON
   * @throws IOException si une erreur survient lors de la lecture du fichier
   */
  public void loadFromJSON(String filePath) throws IOException {
    // Charger le fichier depuis les ressources
    try (InputStream is = getClass().getResourceAsStream(filePath)) {
      if (is == null) {
        throw new IOException("Impossible de trouver le fichier: " + filePath);
      }
      loadFromJSON(is);
    }
  }

  /**
   * Charge les données du catalogue depuis un flux d'entrée JSON.
   * 
   * @param inputStream le flux d'entrée contenant les données JSON
   * @throws IOException           si une erreur survient lors de la lecture
   * @throws IllegalStateException si une catégorie référencée n'existe pas
   */
  public void loadFromJSON(InputStream inputStream) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(inputStream);

    // Charger les types de pièces
    JsonNode partTypesNode = rootNode.get("partTypes");
    for (JsonNode partTypeNode : partTypesNode) {
      String partTypeName = partTypeNode.get("name").asText();
      String categoryName = partTypeNode.get("category").asText();
      int partTypePrice = partTypeNode.get("price").asInt();

      Category category = categories.get(categoryName);
      if (category == null) {
        throw new IllegalStateException("Category not found: " + categoryName);
      }

      PartType partType = new PartTypeImpl(
          partTypeName,
          category,
          PartImpl.class,
          partTypePrice);
      partTypes.add(partType);
    }
  }

  /**
   * Retourne l'ensemble des catégories disponibles dans le catalogue.
   * 
   * @return un ensemble contenant toutes les catégories
   */
  public Set<Category> getCategories() {
    return new HashSet<>(categories.values());
  }

  /**
   * Retourne une catégorie spécifique par son nom.
   * 
   * @param name le nom de la catégorie
   * @return la catégorie correspondante
   */
  public Category getCategory(String name) {
    return categories.get(name);
  }

  /**
   * Retourne l'ensemble des types de pièces disponibles dans le catalogue.
   * 
   * @return un ensemble contenant tous les types de pièces
   */
  public Set<PartType> getPartTypes() {
    return new HashSet<>(partTypes);
  }

  /**
   * Retourne l'ensemble des types de pièces pour une catégorie spécifique.
   * 
   * @param category la catégorie à filtrer
   * @return un ensemble contenant tous les types de pièces pour la catégorie
   */
  public Set<PartType> getPartTypesForCategory(Category category) {
    return partTypes.stream()
        .filter(pt -> pt.getCategory().equals(category))
        .collect(java.util.stream.Collectors.toSet());
  }
}