package fr.istic.bodin_bodier.cartaylor.api;

import java.util.Optional;
import java.util.Set;

/**
 * Interface pour la gestion des propriétés.
 */
public interface PropertyManager {
  /**
   * Retourne un ensemble immuable des noms des propriétés supportées par le
   * gestionnaire de propriétés.
   * manager.
   *
   * @return
   */
  Set<String> getPropertyNames();

  /**
   * Returns the immutable set of discrete string values for a given property.
   * For properties that have a non explicit set of possible values (eg double
   * converted to strings),
   * or for a non existing property name, returns an empty set.
   *
   * @param propertyName a non-null string reference
   * @return an immutable set (see above)
   */
  Set<String> getAvailablePropertyValues(String propertyName);

  /**
   * Returns the optional value of a property.
   * If the object does not support that property then an empty optional is
   * returned.
   * 
   * @param propertyName the property to read
   * @return
   */
  Optional<String> getProperty(String propertyName);

  /**
   * Sets the value of a given property.
   * If there is not such property, or if it not writable, or if the value is
   * invalid
   * then an IllegalArgumentException is thrown.
   * 
   * @param propertyName
   * @param propertyValue
   * @throws IllegalArgumentException (see above)
   */
  void setProperty(String propertyName, String propertyValue);

}
