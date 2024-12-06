package fr.istic.bodin_bodier.cartaylor.impl.categories;

import fr.istic.bodin_bodier.cartaylor.impl.AbstractCategory;

public class InteriorCategory extends AbstractCategory {
  enum FinishType {
    STANDARD, HIGH_END, SPORT
  }

  enum Material {
    LEATHER, CLOTH, ALCANTARA
  }

  private FinishType finishType;
  private Material material;

  public InteriorCategory() {
    super("Interior");
    this.finishType = FinishType.STANDARD;
    this.material = Material.LEATHER;
  }

  public InteriorCategory(FinishType finishType, Material material) {
    super("Interior");
    this.finishType = finishType;
    this.material = material;
  }

  public FinishType getFinishType() {
    return finishType;
  }

  public Material getMaterial() {
    return material;
  }
}
