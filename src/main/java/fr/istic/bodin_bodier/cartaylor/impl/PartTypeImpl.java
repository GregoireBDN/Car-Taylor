package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.lang.reflect.Constructor;
import java.util.Objects;

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
  private final int price;
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
  public PartTypeImpl(String name, Category category, Class<? extends PartImpl> classRef, int price) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Le nom de la pièce ne peut pas être null ou vide");
    }
    if (category == null) {
      throw new IllegalArgumentException("La catégorie ne peut pas être null");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Le prix ne peut pas être négatif");
    }
    this.name = name;
    this.category = category;
    this.classRef = classRef;
    this.price = price;
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
    PartTypeImpl that = (PartTypeImpl) o;
    return price == that.price &&
        Objects.equals(name, that.name) &&
        Objects.equals(category, that.category);
  }

  /**
   * Retourne le code de hachage de ce type de pièce.
   * 
   * @return le code de hachage de la pièce
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, category, price);
  }

  /**
   * Crée une nouvelle instance de la pièce associée à ce type.
   * 
   * @return une nouvelle instance de la pièce
   */
  public PartImpl newInstance() {
    try {
      Constructor<? extends PartImpl> constructor = classRef.getConstructor();
      return constructor.newInstance();
    } catch (Exception e) {
      Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
      throw new RuntimeException("Failed to create a new instance of PartImpl", e);
    }
  }

  /**
   * Retourne le prix de la pièce.
   * 
   * @return le prix de la pièce
   */
  @Override
  public int getPrice() {
    return price;
  }
}