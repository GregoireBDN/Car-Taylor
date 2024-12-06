package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class ExteriorCategory extends AbstractCategory {

  enum Color {
    RED,
    BLUE,
    GREEN,
    BLACK,
    WHITE,
    GREY
  }

  enum PaintType {
    CLASSIC, METALLIC, SPORT
  }

  private Color color;
  private PaintType paintType;

  public ExteriorCategory() {
    super("Exterior");
    this.color = Color.BLACK;
    this.paintType = PaintType.CLASSIC;
  }

  public Color getColor() {
    return color;
  }

  public PaintType getPaintType() {
    return paintType;
  }
}
