package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class ExteriorCategory extends AbstractCategory {
  public ExteriorCategory() {
    super("Exterior");
  }

  // Propriétés spécifiques à l'extérieur
  private String paintType; // "Classic", "Metallic", "Sport"
  private String color;
}