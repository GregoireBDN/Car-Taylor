package fr.istic.bodin_bodier.cartaylor.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Interface représentant un catalogue de pièces et de catégories.
 * 
 * @see Catalog
 */
public interface Catalog {
  /**
   * Charge les données du catalogue depuis un fichier JSON.
   * 
   * @param filePath le chemin vers le fichier JSON
   * @throws IOException si une erreur survient lors de la lecture du fichier
   */
  void loadFromJSON(String filePath) throws IOException;

  /**
   * Charge les données du catalogue depuis un flux d'entrée JSON.
   * 
   * @param inputStream le flux d'entrée contenant les données JSON
   * @throws IOException si une erreur survient lors de la lecture du flux
   */
  void loadFromJSON(InputStream inputStream) throws IOException;

  /**
   * Retourne l'ensemble des catégories disponibles dans le catalogue.
   * 
   * @return un ensemble contenant toutes les catégories
   */
  Set<Category> getCategories();

  /**
   * Retourne une catégorie spécifique par son nom.
   * 
   * @param name le nom de la catégorie
   * @return la catégorie correspondante
   */
  Category getCategory(String name);

  /**
   * Retourne l'ensemble des types de pièces disponibles dans le catalogue.
   * 
   * @return un ensemble contenant tous les types de pièces
   */
  Set<PartType> getPartTypes();

  /**
   * Retourne l'ensemble des types de pièces pour une catégorie spécifique.
   * 
   * @param category la catégorie à filtrer
   * @return un ensemble contenant tous les types de pièces pour la catégorie
   */
  Set<PartType> getPartTypesForCategory(Category category);
}