package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class InteriorCategory extends AbstractCategory {
  public InteriorCategory() {
    super("Interior");
  }

  // Propriétés spécifiques à l'intérieur
  private String finishType; // "Standard", "High-end", "Sport"
  private String material;
}