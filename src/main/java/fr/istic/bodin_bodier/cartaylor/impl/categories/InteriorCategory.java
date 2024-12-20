package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

/**
 * Classe représentant la catégorie des intérieurs.
 */
public class InteriorCategory extends AbstractCategory {

  /**
   * Enumération des types de finition disponibles.
   */
  enum FinishType {
    STANDARD, HIGH_END, SPORT
  }

  /**
   * Enumération des matériaux disponibles.
   */
  enum Material {
    LEATHER, CLOTH, ALCANTARA
  }

  private FinishType finishType;
  private Material material;

  /**
   * Constructeur par défaut de la catégorie des intérieurs.
   */
  public InteriorCategory() {
    super("Interior");
    this.finishType = FinishType.STANDARD;
    this.material = Material.LEATHER;
  }

  /**
   * Constructeur de la catégorie des intérieurs.
   * 
   * @param finishType le type de finition
   * @param material   le matériau
   */
  public InteriorCategory(FinishType finishType, Material material) {
    super("Interior");
    this.finishType = finishType;
    this.material = material;
  }

  /**
   * Retourne le type de finition de l'intérieur.
   * 
   * @return le type de finition de l'intérieur
   */
  public FinishType getFinishType() {
    return finishType;
  }

  /**
   * Retourne le matériau de l'intérieur.
   * 
   * @return le matériau de l'intérieur
   */
  public Material getMaterial() {
    return material;
  }
}
