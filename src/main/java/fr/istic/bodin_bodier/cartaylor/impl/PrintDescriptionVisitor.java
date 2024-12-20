package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Visitor;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.Configuration;
import fr.istic.bodin_bodier.cartaylor.api.Configurator;
import fr.istic.bodin_bodier.cartaylor.api.Part;

import java.io.PrintStream;

public class PrintDescriptionVisitor implements Visitor {
  private final PrintStream printStream;

  public PrintDescriptionVisitor(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void visit(PartType partType) {
    printStream.println("PartType: " + partType.getName() + ", Price: " + partType.getPrice());
  }

  @Override
  public void visit(Category category) {
    printStream.println("Category: " + category.getName());
  }

  @Override
  public void visit(Configuration configuration) {
    printStream.println("Configuration: " + configuration.toString());
  }

  @Override
  public void visit(Configurator configurator) {
    printStream.println("Configurator: " + configurator.toString());
  }

  @Override
  public void visit(Part part) {
    printStream.println("Part: " + part.toString());
  }
}
