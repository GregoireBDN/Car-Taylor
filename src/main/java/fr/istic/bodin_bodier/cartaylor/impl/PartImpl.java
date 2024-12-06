package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Part;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import fr.istic.bodin_bodier.cartaylor.api.Category;

public class PartImpl implements Part {

  private PartType type;

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

  protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter,
      Set<String> possibleValues) {
    properties.put(name, new Property(getter, setter, possibleValues));
  }

  @Override
  public Set<String> getPropertyNames() {
    return Collections.unmodifiableSet(properties.keySet());
  }

  @Override
  public Optional<String> getProperty(String propertyName) {
    Objects.requireNonNull(propertyName);

    if (properties.containsKey(propertyName)) {
      return Optional.of(properties.get(propertyName).getter.get());
    }
    return Optional.empty();
  }

  @Override
  public void setProperty(String propertyName, String propertyValue) {
    Objects.requireNonNull(propertyName);
    Objects.requireNonNull(propertyValue);

    if ((properties.containsKey(propertyName)) && (properties.get(propertyName).setter != null)) {
      properties.get(propertyName).setter.accept(propertyValue);
    } else {
      throw new IllegalArgumentException("bad property name or value: " + propertyName);
    }
  }

  @Override
  public Set<String> getAvailablePropertyValues(String propertyName) {
    if (properties.containsKey(propertyName)) {
      return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
    }
    return Collections.emptySet();
  }

  @Override
  public PartType getType() {
    return type;
  }

  @Override
  public Category getCategory() {
    return type.getCategory();
  }

}
