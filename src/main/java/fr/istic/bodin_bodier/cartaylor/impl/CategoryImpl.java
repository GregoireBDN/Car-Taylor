package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;

/**
 * Implémentation de l'interface Category représentant une catégorie de pièces
 * dans le configurateur de voiture.
 * 
 * <p>
 * Cette classe assure que chaque catégorie a un nom valide et fournit
 * des méthodes pour accéder à ce nom et comparer des catégories.
 * 
 * @see Category
 */
public class CategoryImpl implements Category {

  private final String name;

  /**
   * Constructeur de la classe CategoryImpl.
   * 
   * @param name le nom de la catégorie
   * @throws IllegalArgumentException si le nom est null ou vide
   */
  public CategoryImpl(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Le nom de la catégorie ne peut pas être null ou vide");
    }
    this.name = name;
  }

  /**
   * Retourne le nom de la catégorie.
   * 
   * @return le nom de la catégorie
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Compare cette catégorie avec un autre objet pour vérifier l'égalité.
   * 
   * @param o l'objet à comparer
   * @return true si les catégories sont identiques, false sinon
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CategoryImpl category = (CategoryImpl) o;
    return name.equals(category.name);
  }

  /**
   * Retourne une représentation sous forme de chaîne de caractères de la
   * catégorie.
   * 
   * @return la représentation en chaîne de la catégorie
   */
  @Override
  public String toString() {
    return "CategoryImpl{name='" + name + "'}";
  }
}