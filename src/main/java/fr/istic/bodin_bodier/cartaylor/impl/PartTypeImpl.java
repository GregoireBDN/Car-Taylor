package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.lang.reflect.Constructor;

/**
 * Implémentation de l'interface PartType représentant un type de pièce
 * spécifique dans le configurateur de voiture.
 * 
 * <p>
 * Cette classe associe un nom unique à une catégorie et garantit
 * l'intégrité des données en vérifiant la validité des paramètres.
 *
 * @see PartType
 */
public class PartTypeImpl implements PartType {
  private final String name;
  private Class<? extends PartImpl> classRef;
  private final Category category;

  /**
   * Constructeur de la classe PartTypeImpl.
   * 
   * @param name     le nom du type de pièce
   * @param category la catégorie à laquelle appartient la pièce
   * @throws IllegalArgumentException si le nom est null ou vide, ou si la
   *                                  catégorie est null
   */
  public PartTypeImpl(String name, Category category, Class<? extends PartImpl> classRef) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Le nom de la pièce ne peut pas être null ou vide");
    }
    if (category == null) {
      throw new IllegalArgumentException("La catégorie ne peut pas être null");
    }
    this.name = name;
    this.category = category;
    this.classRef = classRef;
  }

  /**
   * Retourne le nom du type de pièce.
   * 
   * @return le nom du type de pièce
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Retourne la catégorie à laquelle appartient ce type de pièce.
   * 
   * @return la catégorie de la pièce
   */
  @Override
  public Category getCategory() {
    return this.category;
  }

  /**
   * Compare ce type de pièce avec un autre objet pour vérifier l'égalité.
   * Deux types de pièces sont considérés égaux s'ils ont le même nom et la même
   * catégorie.
   * 
   * @param o l'objet à comparer
   * @return true si les types de pièces sont identiques, false sinon
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    PartTypeImpl partType = (PartTypeImpl) o;
    return name.equals(partType.name) && category.equals(partType.category);
  }

  public PartImpl newInstance() {
    Constructor<? extends PartImpl> constructor;
    try {
      constructor = classRef.getConstructor();
      return constructor.newInstance();
    } catch (Exception e) {
      Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
      System.exit(-1);
    }
    return null;
  }
}