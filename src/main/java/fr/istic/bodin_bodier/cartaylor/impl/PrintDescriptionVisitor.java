package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Visitor;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.Configuration;
import fr.istic.bodin_bodier.cartaylor.api.Configurator;
import fr.istic.bodin_bodier.cartaylor.api.Part;

import java.io.PrintStream;

/**
 * Implémentation du visiteur pour l'impression de la description.
 */
public class PrintDescriptionVisitor implements Visitor {
  private final PrintStream printStream;

  /**
   * Constructeur de la classe PrintDescriptionVisitor.
   * 
   * @param printStream le flux de sortie
   */
  public PrintDescriptionVisitor(PrintStream printStream) {
    this.printStream = printStream;
  }

  /**
   * Visite un type de pièce.
   * 
   * @param partType le type de pièce
   */
  @Override
  public void visit(PartType partType) {
    partType.getCategory().accept(this);
    printStream.println("PartType: " + partType.getName() + ", Price: " + partType.getPrice());

  }

  /**
   * Visite une catégorie.
   * 
   * @param category la catégorie
   */
  @Override
  public void visit(Category category) {
    printStream.println("Category: " + category.getName());
  }

  /**
   * Visite une configuration.
   * 
   * @param configuration la configuration
   */
  @Override
  public void visit(Configuration configuration) {
    printStream.println("Configuration: ");
    configuration.getSelectedParts().forEach(partType -> partType.accept(this));
  }

  /**
   * Visite une pièce.
   * 
   * @param part la pièce
   */
  @Override
  public void visit(Part part) {
  }
}
