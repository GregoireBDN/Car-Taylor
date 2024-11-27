package fr.istic.bodin_bodier.cartaylor.gui;

import fr.istic.bodin_bodier.cartaylor.api.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Panneau de configuration du véhicule dans le configurateur de voiture.
 * 
 * <p>
 * Ce panneau permet à l'utilisateur de sélectionner des options pour chaque
 * catégorie
 * de pièces et de valider la configuration choisie.
 *
 * @see ConfigurationPanel
 */
public class ConfigurationPanel extends VBox {
  private final Configurator configurator;
  private final Button validateButton;
  private final Map<Category, ComboBox<PartType>> categoryBoxes = new HashMap<>();

  /**
   * Constructeur du panneau de configuration.
   * 
   * @param configurator le configurateur utilisé pour gérer la configuration du
   *                     véhicule
   */
  public ConfigurationPanel(Configurator configurator) {
    this.configurator = configurator;
    setPadding(new Insets(20));
    setSpacing(15);
    setStyle("-fx-background-color: #f5f5f5;");

    // En-tête
    VBox headerBox = new VBox(5);
    headerBox.setAlignment(Pos.CENTER);

    Label title = new Label("Configuration du véhicule");
    title.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

    Label subtitle = new Label("Sélectionnez les options pour votre véhicule");
    subtitle.setStyle("-fx-font-style: italic; -fx-text-fill: #666666;");

    headerBox.getChildren().addAll(title, subtitle);

    // Contenu principal
    VBox contentBox = new VBox(15);
    contentBox.setPadding(new Insets(10));
    contentBox.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

    for (Category category : configurator.getCategories()) {
      TitledPane categoryPane = createCategoryPane(category);
      contentBox.getChildren().add(categoryPane);
    }

    ScrollPane scrollPane = new ScrollPane(contentBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background-color: transparent;");

    // Boutons
    validateButton = new Button("Valider la configuration");
    validateButton.setOnAction(e -> validateConfiguration());
    updateValidationButton();

    Button clearButton = new Button("Réinitialiser");
    clearButton.setOnAction(e -> clearConfiguration());

    HBox buttonBox = new HBox(10, validateButton, clearButton);
    buttonBox.setAlignment(Pos.CENTER);

    getChildren().addAll(headerBox, scrollPane, buttonBox);
  }

  /**
   * Crée un panneau pour une catégorie donnée, permettant la sélection d'options.
   * 
   * @param category la catégorie pour laquelle créer le panneau
   * @return un TitledPane contenant les options de la catégorie
   */
  private TitledPane createCategoryPane(Category category) {
    VBox content = new VBox(5);

    ComboBox<PartType> comboBox = new ComboBox<>();
    comboBox.setPromptText("Sélectionnez une option");
    comboBox.getItems().addAll(configurator.getVariants(category));

    // Personnaliser l'affichage des items
    comboBox.setCellFactory(lv -> new ListCell<PartType>() {
      @Override
      protected void updateItem(PartType item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getName());
      }
    });

    comboBox.setButtonCell(new ListCell<PartType>() {
      @Override
      protected void updateItem(PartType item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getName());
      }
    });

    comboBox.setOnAction(e -> {
      PartType selected = comboBox.getValue();
      if (selected != null) {
        configurator.getConfiguration().selectPart(selected);
        updateValidationButton();
      }
    });

    categoryBoxes.put(category, comboBox);
    content.getChildren().add(comboBox);

    return new TitledPane(category.getName(), content);
  }

  /**
   * Valide la configuration actuelle et affiche un message d'alerte en fonction
   * de son état.
   */
  private void validateConfiguration() {
    Configuration config = configurator.getConfiguration();
    Alert alert;

    if (!config.isComplete()) {
      alert = new Alert(Alert.AlertType.WARNING,
          "Configuration incomplète. Veuillez sélectionner une option pour chaque catégorie.");
    } else if (!config.isValid()) {
      alert = new Alert(Alert.AlertType.ERROR,
          "Configuration invalide. Certaines pièces sont incompatibles.");
    } else {
      alert = new Alert(Alert.AlertType.INFORMATION,
          "Configuration valide !");
    }
    alert.showAndWait();
  }

  /**
   * Met à jour l'état du bouton de validation en fonction de la validité de la
   * configuration.
   */
  public void updateValidationButton() {
    Configuration config = configurator.getConfiguration();

    if (!config.isValid() && !config.getSelectedParts().isEmpty()) {
      StringBuilder message = new StringBuilder("Incompatibilités détectées entre : ");
      Set<PartType> selectedParts = config.getSelectedParts();

      for (PartType part : selectedParts) {
        Set<PartType> incompatibilities = configurator.getCompatibilityChecker()
            .getIncompatibilities(part);

        Set<PartType> commonParts = new HashSet<>(incompatibilities);
        commonParts.retainAll(selectedParts);

        if (!commonParts.isEmpty()) {
          message.append(part.getName())
              .append(" et ")
              .append(commonParts.stream()
                  .map(PartType::getName)
                  .collect(Collectors.joining(", ")))
              .append("; ");
        }
      }

      validateButton.setText("Configuration invalide");
      validateButton.setStyle("-fx-background-color: #FF6B6B;");
      validateButton.setTooltip(new Tooltip(message.toString()));
    } else if (!config.isComplete()) {
      validateButton.setText("Configuration incomplète");
      validateButton.setStyle("-fx-background-color: #FFA500;");
      validateButton.setTooltip(new Tooltip("Sélectionnez une option pour chaque catégorie"));
    } else {
      validateButton.setText("Configuration valide");
      validateButton.setStyle("-fx-background-color: #90EE90;");
      validateButton.setTooltip(new Tooltip("La configuration est valide"));
    }
  }

  /**
   * Réinitialise la configuration en cours, effaçant toutes les sélections.
   */
  private void clearConfiguration() {
    configurator.getConfiguration().clear();
    categoryBoxes.values().forEach(comboBox -> comboBox.getSelectionModel().clearSelection());
    updateValidationButton();
  }
}