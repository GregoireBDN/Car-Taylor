package fr.istic.bodin_bodier.cartaylor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;

import java.io.*;
import java.util.*;

/**
 * Classe gérant le catalogue des pièces et catégories disponibles
 * dans le configurateur de voiture.
 * 
 * <p>
 * Cette classe permet le chargement des données depuis un fichier JSON
 * et maintient la cohérence entre les catégories et les types de pièces.
 * 
 * @see Catalogue
 */
public class Catalogue {

  /** Map associant les noms des catégories à leurs instances */
  private final Map<String, Category> categories = new HashMap<>();
  /** Ensemble des types de pièces disponibles */
  private final Set<PartType> partTypes = new HashSet<>();

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

    // Charger les catégories
    JsonNode categoriesNode = rootNode.get("categories");
    for (JsonNode categoryNode : categoriesNode) {
      String categoryName = categoryNode.get("name").asText();
      categories.put(categoryName, new CategoryImpl(categoryName));
    }

    // Charger les types de pièces
    JsonNode partTypesNode = rootNode.get("partTypes");
    for (JsonNode partTypeNode : partTypesNode) {
      String partTypeName = partTypeNode.get("name").asText();
      String categoryName = partTypeNode.get("category").get("name").asText();

      Category category = categories.get(categoryName);
      if (category == null) {
        throw new IllegalStateException("Category not found: " + categoryName);
      }

      PartType partType = new PartTypeImpl(partTypeName, category);
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
   * Retourne l'ensemble des types de pièces disponibles dans le catalogue.
   * 
   * @return un ensemble contenant tous les types de pièces
   */
  public Set<PartType> getPartTypes() {
    return new HashSet<>(partTypes);
  }
}