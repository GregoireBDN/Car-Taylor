package fr.istic.bodin_bodier.cartaylor.gui;

import fr.istic.bodin_bodier.cartaylor.api.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Panneau d'affichage du résumé des règles de compatibilité dans le
 * configurateur de voiture.
 * 
 * <p>
 * Ce panneau affiche une vue d'ensemble des incompatibilités et des nécessités
 * pour chaque catégorie et type de pièce.
 *
 * @see SummaryPanel
 */
public class SummaryPanel extends VBox {
  private final Configurator configurator;

  /**
   * Constructeur du panneau de résumé.
   * 
   * @param configurator le configurateur utilisé pour obtenir les informations de
   *                     compatibilité
   */
  public SummaryPanel(Configurator configurator) {
    this.configurator = configurator;
    setPadding(new Insets(20));
    setSpacing(15);

    // En-tête
    VBox headerBox = new VBox(5);
    headerBox.setAlignment(Pos.CENTER);

    Label title = new Label("Résumé des Compatibilités");
    title.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

    Label subtitle = new Label("Vue d'ensemble des règles de compatibilité");
    subtitle.setStyle("-fx-font-style: italic; -fx-text-fill: #666666;");

    headerBox.getChildren().addAll(title, subtitle);

    // Contenu principal
    VBox contentBox = new VBox(15);
    contentBox.setPadding(new Insets(10));

    ScrollPane scrollPane = new ScrollPane(contentBox);
    scrollPane.setFitToWidth(true);

    getChildren().addAll(headerBox, scrollPane);

    updateContent(contentBox);
  }

  /**
   * Met à jour le contenu du panneau avec les dernières informations de
   * compatibilité.
   * 
   * @param contentBox le conteneur VBox à mettre à jour
   */
  private void updateContent(VBox contentBox) {
    contentBox.getChildren().clear();

    for (Category category : configurator.getCategories()) {
      TitledPane categoryPane = createCategoryPane(category);
      contentBox.getChildren().add(categoryPane);
    }
  }

  /**
   * Crée un panneau pour une catégorie donnée, affichant les règles de
   * compatibilité.
   * 
   * @param category la catégorie pour laquelle créer le panneau
   * @return un TitledPane contenant les informations de compatibilité de la
   *         catégorie
   */
  private TitledPane createCategoryPane(Category category) {
    VBox content = new VBox(10);
    content.setPadding(new Insets(10));

    Label categoryLabel = new Label("Règles pour " + category.getName().toLowerCase());
    categoryLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #666666;");
    content.getChildren().add(categoryLabel);

    for (PartType partType : configurator.getVariants(category)) {
      TitledPane partPane = createPartPane(partType);
      content.getChildren().add(partPane);
    }

    return new TitledPane(category.getName(), content);
  }

  /**
   * Crée un panneau pour un type de pièce donné, affichant ses incompatibilités
   * et nécessités.
   * 
   * @param partType le type de pièce pour lequel créer le panneau
   * @return un TitledPane contenant les informations de compatibilité du type de
   *         pièce
   */
  private TitledPane createPartPane(PartType partType) {
    VBox content = new VBox(10);
    content.setPadding(new Insets(10));

    // Incompatibilités
    VBox incompBox = new VBox(5);
    Label incompTitle = new Label("Incompatibilités");
    incompTitle.setStyle("-fx-font-weight: bold;");

    Set<PartType> incompatibilities = configurator.getCompatibilityChecker().getIncompatibilities(partType);
    Label incompList = new Label(incompatibilities.isEmpty() ? "Aucune incompatibilité"
        : incompatibilities.stream()
            .map(PartType::getName)
            .collect(Collectors.joining(", ")));
    incompList.setWrapText(true);
    incompBox.getChildren().addAll(incompTitle, incompList);

    // Nécessités
    VBox reqBox = new VBox(5);
    Label reqTitle = new Label("Nécessités");
    reqTitle.setStyle("-fx-font-weight: bold;");

    Set<PartType> requirements = configurator.getCompatibilityChecker().getRequirements(partType);
    Label reqList = new Label(requirements.isEmpty() ? "Aucune nécessité"
        : requirements.stream()
            .map(PartType::getName)
            .collect(Collectors.joining(", ")));
    reqList.setWrapText(true);
    reqBox.getChildren().addAll(reqTitle, reqList);

    content.getChildren().addAll(incompBox, reqBox);
    return new TitledPane(partType.getName(), content);
  }

  /**
   * Met à jour le résumé des compatibilités affiché dans le panneau.
   */
  public void updateSummary() {
    VBox contentBox = (VBox) ((ScrollPane) getChildren().get(1)).getContent();
    updateContent(contentBox);
  }
}