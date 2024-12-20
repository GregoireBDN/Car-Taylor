package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

/**
 * Classe représentant la catégorie des carrosseries.
 */
public class ExteriorCategory extends AbstractCategory {

  /**
   * Enumération des couleurs disponibles.
   */
  enum Color {
    RED,
    BLUE,
    GREEN,
    BLACK,
    WHITE,
    GREY
  }

  /**
   * Enumération des types de peinture disponibles.
   */
  enum PaintType {
    CLASSIC, METALLIC, SPORT
  }

  private Color color;
  private PaintType paintType;

  /**
   * Constructeur de la catégorie des carrosseries.
   */
  public ExteriorCategory() {
    super("Exterior");
    this.color = Color.BLACK;
    this.paintType = PaintType.CLASSIC;
  }

  /**
   * Retourne la couleur de la carrosserie.
   * 
   * @return la couleur de la carrosserie
   */
  public Color getColor() {
    return color;
  }

  /**
   * Retourne le type de peinture de la carrosserie.
   * 
   * @return le type de peinture de la carrosserie
   */
  public PaintType getPaintType() {
    return paintType;
  }
}
