package fr.istic.bodin_bodier.cartaylor.gui;

import fr.istic.bodin_bodier.cartaylor.api.Configurator;
import fr.istic.bodin_bodier.cartaylor.impl.ConfiguratorImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Application principale du configurateur de voiture Car Taylor.
 * 
 * <p>
 * Cette classe initialise l'interface graphique principale et gère
 * l'organisation des différents panneaux de l'application :
 * <ul>
 * <li>Panneau de configuration</li>
 * <li>Panneau de compatibilité</li>
 * <li>Panneau de résumé</li>
 * </ul>
 *
 * @author Gregoire Bodin & Leo Bernard-Bodier
 * @see CarTaylorApp
 */
public class CarTaylorApp extends Application {
  private Configurator configurator;

  /**
   * Initialise et affiche la fenêtre principale de l'application.
   * 
   * @param primaryStage la fenêtre principale de l'application JavaFX
   */
  @Override
  public void start(Stage primaryStage) {
    configurator = new ConfiguratorImpl("data/catalogue.json");

    TabPane tabPane = new TabPane();

    SummaryPanel summaryPanel = new SummaryPanel(configurator);
    ConfigurationPanel configPanel = new ConfigurationPanel(configurator);

    Tab configTab = new Tab("Configuration");
    configTab.setContent(configPanel);

    Tab compatibilityTab = new Tab("Compatibilité");
    compatibilityTab.setContent(new CompatibilityPanel(configurator, summaryPanel, configPanel));

    Tab summaryTab = new Tab("Résumé");
    summaryTab.setContent(summaryPanel);

    tabPane.getTabs().addAll(configTab, compatibilityTab, summaryTab);

    Scene scene = new Scene(tabPane, 800, 600);
    primaryStage.setTitle("Car Taylor Configurator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Point d'entrée principal de l'application.
   * 
   * @param args les arguments de la ligne de commande (non utilisés)
   */
  public static void main(String[] args) {
    launch(args);
  }
}