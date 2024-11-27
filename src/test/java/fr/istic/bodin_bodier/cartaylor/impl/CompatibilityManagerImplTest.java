package fr.istic.bodin_bodier.cartaylor.impl;

import fr.istic.bodin_bodier.cartaylor.api.Category;
import fr.istic.bodin_bodier.cartaylor.api.PartType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests unitaires pour la classe CompatibilityManagerImpl.
 * 
 * <p>
 * Cette classe vérifie la gestion des incompatibilités et des exigences
 * entre les différentes pièces du configurateur. Elle teste notamment :
 * <ul>
 * <li>L'ajout et la suppression d'incompatibilités</li>
 * <li>L'ajout et la suppression d'exigences</li>
 * <li>La bidirectionnalité des relations d'incompatibilité</li>
 * <li>La gestion des cas d'erreur</li>
 * </ul>
 *
 */
public class CompatibilityManagerImplTest {

  private CompatibilityManagerImpl compatibilityManager;
  private PartType partA;
  private PartType partB;
  private PartType partC;
  private Set<PartType> targetSet;

  /**
   * Initialise l'environnement de test avant chaque test.
   * 
   * <p>
   * Crée un nouveau gestionnaire de compatibilité et initialise
   * les pièces de test avec leurs relations.
   */
  @Before
  public void setUp() {
    compatibilityManager = new CompatibilityManagerImpl();
    partA = new MockPartType("A");
    partB = new MockPartType("B");
    partC = new MockPartType("C");
    targetSet = new HashSet<>();
    targetSet.add(partB);
    targetSet.add(partC);
  }

  /**
   * Vérifie que l'ajout d'incompatibilités fonctionne correctement.
   * 
   * <p>
   * Ce test s'assure que :
   * <ul>
   * <li>Les incompatibilités sont correctement ajoutées</li>
   * <li>La relation est bidirectionnelle</li>
   * <li>Toutes les pièces cibles sont bien prises en compte</li>
   * </ul>
   */
  @Test
  public void testAddIncompatibilities() {
    compatibilityManager.addIncompatibilities(partA, targetSet);

    Set<PartType> incompatibilities = compatibilityManager.getIncompatibilities(partA);
    assertTrue(incompatibilities.contains(partB));
    assertTrue(incompatibilities.contains(partC));

    // Vérification de la bidirectionnalité
    assertTrue(compatibilityManager.getIncompatibilities(partB).contains(partA));
    assertTrue(compatibilityManager.getIncompatibilities(partC).contains(partA));
  }

  /**
   * Vérifie que la suppression d'incompatibilités fonctionne correctement.
   * 
   * <p>
   * Ce test s'assure que :
   * <ul>
   * <li>L'incompatibilité est correctement supprimée</li>
   * <li>La relation est supprimée dans les deux sens</li>
   * <li>Les autres incompatibilités restent inchangées</li>
   * </ul>
   */
  @Test
  public void testRemoveIncompatibility() {
    compatibilityManager.addIncompatibilities(partA, targetSet);
    compatibilityManager.removeIncompatibility(partA, partB);

    assertFalse(compatibilityManager.getIncompatibilities(partA).contains(partB));
    assertFalse(compatibilityManager.getIncompatibilities(partB).contains(partA));
    assertTrue(compatibilityManager.getIncompatibilities(partA).contains(partC));
  }

  /**
   * Vérifie que l'ajout d'exigences fonctionne correctement.
   * 
   * <p>
   * Ce test s'assure que :
   * <ul>
   * <li>Les exigences sont correctement ajoutées</li>
   * <li>Toutes les pièces requises sont bien enregistrées</li>
   * </ul>
   */
  @Test
  public void testAddRequirements() {
    compatibilityManager.addRequirements(partA, targetSet);

    Set<PartType> requirements = compatibilityManager.getRequirements(partA);
    assertTrue(requirements.contains(partB));
    assertTrue(requirements.contains(partC));
  }

  /**
   * Vérifie que la suppression d'exigences fonctionne correctement.
   * 
   * <p>
   * Ce test s'assure que :
   * <ul>
   * <li>L'exigence est correctement supprimée</li>
   * <li>Les autres exigences restent inchangées</li>
   * </ul>
   */
  @Test
  public void testRemoveRequirement() {
    compatibilityManager.addRequirements(partA, targetSet);
    compatibilityManager.removeRequirement(partA, partB);

    assertFalse(compatibilityManager.getRequirements(partA).contains(partB));
    assertTrue(compatibilityManager.getRequirements(partA).contains(partC));
  }

  /**
   * Vérifie que les exceptions appropriées sont levées pour les paramètres null.
   * 
   * @throws IllegalArgumentException attendue lors de l'utilisation de paramètres
   *                                  null
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullParameters() {
    compatibilityManager.addIncompatibilities(null, targetSet);
  }

  /**
   * Classe mock pour les tests de types de pièces.
   * 
   * <p>
   * Cette implémentation simplifiée de PartType permet de tester
   * le gestionnaire de compatibilité sans dépendre de l'implémentation réelle.
   */
  private static class MockPartType implements PartType {
    private final String name;

    /**
     * Crée un nouveau type de pièce mock avec le nom spécifié.
     * 
     * @param name le nom de la pièce mock
     */
    public MockPartType(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public Category getCategory() {
      return null;

    }

    @Override
    public String toString() {
      return name;
    }
  }
}