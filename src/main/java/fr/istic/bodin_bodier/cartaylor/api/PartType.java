package fr.istic.bodin_bodier.cartaylor.api;

import fr.istic.bodin_bodier.cartaylor.impl.PartImpl;

/**
 * Interface représentant un type de pièce dans le configurateur de véhicule.
 * 
 * <p>
 * Un type de pièce est caractérisé par un nom unique et appartient à une
 * catégorie spécifique. Par exemple, "Moteur V6" est un type de pièce
 * appartenant à la catégorie "Moteur".
 *
 * @see Category
 */
public interface PartType extends Element {

  /**
   * Retourne le nom du type de pièce.
   * 
   * @return le nom unique identifiant le type de pièce
   */
  String getName();

  /**
   * Retourne le prix du type de pièce.
   * 
   * @return le prix du type de pièce
   */
  int getPrice();

  /**
   * Retourne la catégorie à laquelle appartient ce type de pièce.
   * 
   * @return la catégorie de la pièce
   */
  Category getCategory();

  /**
   * Retourne une nouvelle instance de la pièce associée à ce type.
   * 
   * @return une nouvelle instance de la pièce associée à ce type
   */
  PartImpl newInstance();
}