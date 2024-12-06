package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class ExteriorCategory extends AbstractCategory {
  public ExteriorCategory() {
    super("Exterior");
    this.color = Color.BLACK;
    this.paintType = "Classic";
  }

   
  public enum Color {
    RED,
    BLUE,
    GREEN,
    BLACK,
    WHITE,
    GREY
  }

  private Color color;
  // Propriétés spécifiques à l'extérieur
  private String paintType; // "Classic", "Metallic", "Sport"
 
  
}
