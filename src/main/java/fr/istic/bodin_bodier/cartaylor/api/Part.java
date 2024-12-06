package fr.istic.bodin_bodier.cartaylor.api;

import java.util.Optional;
import java.util.Set;

/**
 * Interface représentant une pièce dans le configurateur de véhicule.
 */
public interface Part {
  default String getName() {
    return this.getClass().getTypeName();
  }

  Category getCategory();

  PartType getType();

  Set<String> getPropertyNames();

  Optional<String> getProperty(String propertyName);

  void setProperty(String propertyName, String propertyValue);

  Set<String> getAvailablePropertyValues(String propertyName);
}
