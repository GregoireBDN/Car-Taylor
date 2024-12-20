package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;

import fr.istic.bodin_bodier.cartaylor.api.Visitor;
import java.util.Objects;

/**
 * Classe abstraite représentant une catégorie de pièces.
 */
public abstract class AbstractCategory implements Category {
  private final String name;

  /**
   * Constructeur de la classe AbstractCategory.
   * 
   * @param name le nom de la catégorie
   */
  protected AbstractCategory(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Le nom de la catégorie ne peut pas être null ou vide");
    }
    this.name = name;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  /**
   * Retourne le nom de la catégorie.
   * 
   * @return le nom de la catégorie
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Vérifie l'égalité avec un autre objet.
   * 
   * @param o l'objet à comparer
   * @return true si les objets sont égaux, false sinon
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AbstractCategory that = (AbstractCategory) o;
    return Objects.equals(name, that.name);
  }

  /**
   * Retourne le code de hachage de la catégorie.
   * 
   * @return le code de hachage de la catégorie
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}