package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;

public abstract class AbstractCategory implements Category {
  private final String name;

  protected AbstractCategory(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Le nom de la catégorie ne peut pas être null ou vide");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AbstractCategory that = (AbstractCategory) o;
    return name.equals(that.name);
  }
}