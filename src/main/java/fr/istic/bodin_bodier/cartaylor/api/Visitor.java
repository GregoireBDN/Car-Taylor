package fr.istic.bodin_bodier.cartaylor.api;

/**
 * Interface représentant un visiteur.
 */
public interface Visitor {
  /**
   * Visite un type de pièce.
   * 
   * @param partType le type de pièce
   */
  void visit(PartType partType);

  /**
   * Visite une catégorie.
   * 
   * @param category la catégorie
   */
  void visit(Category category);

  /**
   * Visite une configuration.
   * 
   * @param configuration la configuration
   */
  void visit(Configuration configuration);

  /**
   * Visite une pièce.
   * 
   * @param part la pièce
   */
  void visit(Part part);
}
