package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Part;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.Element;
import fr.istic.bodin_bodier.cartaylor.api.Visitor;

/**
 * Implémentation de la classe Part.
 */
public class PartImpl implements Part, Element {

  private PartType type;

  /**
   * Constructeur de la classe PartImpl.
   * 
   * @param type Le type de la pièce.
   */
  public PartImpl(PartType type) {
    this.type = type;
  }

  /**
   * Constructeur par défaut de la classe PartImpl.
   */
  public PartImpl() {
    this.type = null;
  }

  /**
   * Classe interne pour représenter une propriété d'une pièce.
   */
  private class Property {
    public final Supplier<String> getter;
    public final Consumer<String> setter;
    public final Set<String> possibleValues;

    Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
      this.getter = getter;
      this.setter = setter;
      this.possibleValues = possibleValues;

    }
  }

  private Map<String, Property> properties = new HashMap<>();

  /**
   * Ajoute une propriété à la pièce.
   * 
   * @param name           Le nom de la propriété.
   * @param getter         Le getter pour la propriété.
   * @param setter         Le setter pour la propriété.
   * @param possibleValues Les valeurs possibles pour la propriété.
   */
  protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter,
      Set<String> possibleValues) {
    properties.put(name, new Property(getter, setter, possibleValues));
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  /**
   * Retourne les noms des propriétés de la pièce.
   * 
   * @return Un ensemble non modifiable des noms des propriétés.
   */
  @Override
  public Set<String> getPropertyNames() {
    return Collections.unmodifiableSet(properties.keySet());
  }

  /**
   * Retourne la valeur d'une propriété de la pièce.
   * 
   * @param propertyName Le nom de la propriété.
   * @return La valeur de la propriété, ou Optional.empty() si la propriété
   *         n'existe
   *         pas.
   */
  @Override
  public Optional<String> getProperty(String propertyName) {
    Objects.requireNonNull(propertyName);

    if (properties.containsKey(propertyName)) {
      return Optional.of(properties.get(propertyName).getter.get());
    }
    return Optional.empty();
  }

  /**
   * Définit la valeur d'une propriété de la pièce.
   * 
   * @param propertyName  Le nom de la propriété.
   * @param propertyValue La valeur à définir.
   */
  @Override
  public void setProperty(String propertyName, String propertyValue) {
    Objects.requireNonNull(propertyName);
    Objects.requireNonNull(propertyValue);

    if (properties.containsKey(propertyName)) {
      Property property = properties.get(propertyName);
      if (property.setter != null && property.possibleValues.contains(propertyValue)) {
        property.setter.accept(propertyValue);
      } else {
        throw new IllegalArgumentException("Valeur de propriété invalide : " + propertyValue);
      }
    } else {
      throw new IllegalArgumentException("bad property name or value: " + propertyName);
    }
  }

  /**
   * Retourne les valeurs possibles pour une propriété de la pièce.
   * 
   * @param propertyName Le nom de la propriété.
   * @return Un ensemble non modifiable des valeurs possibles pour la propriété,
   *         ou un ensemble vide si la propriété n'existe pas.
   */
  @Override
  public Set<String> getAvailablePropertyValues(String propertyName) {
    if (properties.containsKey(propertyName)) {
      return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
    }
    return Collections.emptySet();
  }

  /**
   * Retourne le type de la pièce.
   * 
   * @return Le type de la pièce.
   */
  @Override
  public PartType getType() {
    return type;
  }

  /**
   * Retourne la catégorie de la pièce.
   * 
   * @return La catégorie de la pièce.
   */
  @Override
  public Category getCategory() {
    return type.getCategory();
  }

}
