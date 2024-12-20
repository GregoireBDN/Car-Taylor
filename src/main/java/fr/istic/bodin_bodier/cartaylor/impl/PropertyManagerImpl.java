package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.PropertyManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Implémentation de l'interface PropertyManager.
 */
public class PropertyManagerImpl implements PropertyManager {
  private Map<String, String> properties = new HashMap<>();
  private Map<String, Set<String>> availableValues = new HashMap<>();

  /**
   * Constructeur de la classe PropertyManagerImpl.
   */
  public PropertyManagerImpl() {
    this.properties = new HashMap<>();
    this.availableValues = new HashMap<>();
  }

  /**
   * Retourne un ensemble immuable des noms des propriétés supportées par le
   * gestionnaire de propriétés.
   * 
   * @return un ensemble immuable des noms des propriétés supportées
   */
  @Override
  public Set<String> getPropertyNames() {
    return Collections.unmodifiableSet(properties.keySet());
  }

  /**
   * Retourne un ensemble immuable des valeurs possibles pour une propriété
   * donnée.
   * 
   * @param propertyName le nom de la propriété
   * @return un ensemble immuable des valeurs possibles pour la propriété
   */
  @Override
  public Set<String> getAvailablePropertyValues(String propertyName) {
    return availableValues.getOrDefault(propertyName, Collections.emptySet());
  }

  /**
   * Retourne l'optionnel de la valeur d'une propriété.
   * 
   * @param propertyName le nom de la propriété
   * @return l'optionnel de la valeur de la propriété
   */
  @Override
  public Optional<String> getProperty(String propertyName) {
    return Optional.ofNullable(properties.get(propertyName));
  }

  /**
   * Définit la valeur d'une propriété.
   * 
   * @param propertyName  le nom de la propriété
   * @param propertyValue la valeur de la propriété
   * @throws IllegalArgumentException si le nom de la propriété n'est pas valide
   *                                  ou si la valeur n'est pas dans les valeurs
   *                                  possibles
   */
  @Override
  public void setProperty(String propertyName, String propertyValue) {
    if (!availableValues.containsKey(propertyName) || !availableValues.get(propertyName).contains(propertyValue)) {
      throw new IllegalArgumentException("Invalid property name or value");
    }
    properties.put(propertyName, propertyValue);
  }

  /**
   * Ajoute une nouvelle propriété avec ses valeurs possibles.
   * 
   * @param propertyName   le nom de la propriété
   * @param possibleValues les valeurs possibles pour la propriété
   */
  public void addProperty(String propertyName, Set<String> possibleValues) {
    availableValues.put(propertyName, possibleValues);
    properties.put(propertyName, null);
  }
}