package fr.istic.bodin_bodier.cartaylor.gui;

import fr.istic.bodin_bodier.cartaylor.api.Configurator;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import fr.istic.bodin_bodier.cartaylor.api.CompatibilityManager;

import java.util.Set;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.util.HashSet;
import java.util.Collections;
import java.util.stream.Collectors;
import javafx.geometry.Pos;

/**
 * Panneau de gestion des règles de compatibilité dans l'interface graphique.
 * 
 * <p>
 * Ce panneau permet aux utilisateurs de :
 * <ul>
 * <li>Visualiser les règles de compatibilité existantes</li>
 * <li>Ajouter de nouvelles règles d'incompatibilité</li>
 * <li>Ajouter de nouvelles règles d'exigence</li>
 * <li>Supprimer des règles existantes</li>
 * </ul>
 *
 * @see CompatibilityPanel
 */
public class CompatibilityPanel extends VBox {
  private final Configurator configurator;
  private final SummaryPanel summaryPanel;
  private final ConfigurationPanel configPanel;

  /**
   * Constructeur du panneau de gestion des compatibilités.
   * 
   * @param configurator le configurateur utilisé pour gérer les règles
   * @param summaryPanel le panneau de résumé à mettre à jour
   * @param configPanel  le panneau de configuration à mettre à jour
   */
  public CompatibilityPanel(Configurator configurator, SummaryPanel summaryPanel, ConfigurationPanel configPanel) {
    this.configurator = configurator;
    this.summaryPanel = summaryPanel;
    this.configPanel = configPanel;

    setPadding(new Insets(20));
    setSpacing(15);

    // En-tête
    VBox headerBox = new VBox(5);
    headerBox.setAlignment(Pos.CENTER);

    Label title = new Label("Gestion des Compatibilités");
    title.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

    Label subtitle = new Label("Définissez les règles de compatibilité entre les pièces");
    subtitle.setStyle("-fx-font-style: italic; -fx-text-fill: #666666;");

    headerBox.getChildren().addAll(title, subtitle);

    // Contenu principal
    VBox contentBox = new VBox(15);
    contentBox.setPadding(new Insets(10));

    for (Category category : configurator.getCategories()) {
      TitledPane categoryPane = createCategoryPane(category);
      contentBox.getChildren().add(categoryPane);
    }

    ScrollPane scrollPane = new ScrollPane(contentBox);
    scrollPane.setFitToWidth(true);

    getChildren().addAll(headerBox, scrollPane);
  }

  /**
   * Crée un panneau pour une catégorie donnée contenant ses règles de
   * compatibilité.
   * 
   * @param category la catégorie pour laquelle créer le panneau
   * @return un TitledPane contenant les contrôles de gestion des règles
   */
  private TitledPane createCategoryPane(Category category) {
    VBox categoryBox = new VBox(10);
    categoryBox.setPadding(new Insets(5));

    for (PartType partType : configurator.getVariants(category)) {
      VBox partBox = new VBox(5);
      partBox.setPadding(new Insets(5));

      Label partLabel = new Label(partType.getName());

      VBox incompSection = createSection("Incompatibilités", partType, true);
      VBox reqSection = createSection("Nécessités", partType, false);

      partBox.getChildren().addAll(partLabel, incompSection, reqSection);
      categoryBox.getChildren().add(partBox);
    }

    return new TitledPane(category.getName(), categoryBox);
  }

  private VBox createSection(String title, PartType partType, boolean isIncomp) {
    VBox section = new VBox(5);
    section.setPadding(new Insets(5));

    Label sectionTitle = new Label(title);

    ComboBox<String> combo = new ComboBox<>();
    combo.setPromptText("Sélectionnez une pièce");
    combo.getItems().addAll(
        getAllOtherParts(partType).stream()
            .map(PartType::getName)
            .collect(Collectors.toList()));

    Button addButton = new Button("Ajouter " + (isIncomp ? "incompatibilité" : "nécessité"));

    VBox listBox = new VBox(5);

    addButton.setOnAction(e -> {
      String selectedName = combo.getValue();
      if (selectedName != null) {
        PartType selected = getAllOtherParts(partType).stream()
            .filter(pt -> pt.getName().equals(selectedName))
            .findFirst()
            .orElse(null);
        if (selected != null) {
          CompatibilityManager manager = (CompatibilityManager) configurator.getCompatibilityChecker();
          if (isIncomp) {
            manager.addIncompatibilities(partType, Collections.singleton(selected));
          } else {
            manager.addRequirements(partType, Collections.singleton(selected));
          }
          updateList(partType, listBox, isIncomp);
          summaryPanel.updateSummary();
          configPanel.updateValidationButton();
        }
      }
    });

    section.getChildren().addAll(sectionTitle, combo, addButton, listBox);
    updateList(partType, listBox, isIncomp);
    return section;
  }

  /**
   * Met à jour la liste des règles affichées pour un type de pièce.
   * 
   * @param partType le type de pièce concerné
   * @param listBox  la boîte contenant la liste à mettre à jour
   * @param isIncomp true pour les incompatibilités, false pour les exigences
   */
  private void updateList(PartType partType, VBox listBox, boolean isIncomp) {
    listBox.getChildren().clear();
    Set<PartType> items = isIncomp ? configurator.getCompatibilityChecker().getIncompatibilities(partType)
        : configurator.getCompatibilityChecker().getRequirements(partType);

    for (PartType item : items) {
      HBox itemBox = new HBox(10);
      itemBox.setAlignment(Pos.CENTER_LEFT);

      Label itemLabel = new Label(item.getName());
      Button removeButton = new Button("Retirer");
      removeButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white;");

      removeButton.setOnAction(e -> {
        CompatibilityManager manager = (CompatibilityManager) configurator.getCompatibilityChecker();
        if (isIncomp) {
          manager.removeIncompatibility(partType, item);
        } else {
          manager.removeRequirement(partType, item);
        }
        updateList(partType, listBox, isIncomp);
        summaryPanel.updateSummary();
        configPanel.updateValidationButton();
      });

      itemBox.getChildren().addAll(itemLabel, removeButton);
      listBox.getChildren().add(itemBox);
    }
  }

  /**
   * Retourne l'ensemble des types de pièces disponibles, à l'exception
   * du type de pièce spécifié.
   * 
   * @param currentPart le type de pièce à exclure
   * @return l'ensemble des autres types de pièces disponibles
   */
  private Set<PartType> getAllOtherParts(PartType currentPart) {
    Set<PartType> allParts = new HashSet<>();
    for (Category cat : configurator.getCategories()) {
      allParts.addAll(configurator.getVariants(cat));
    }
    allParts.remove(currentPart);
    return allParts;
  }

}