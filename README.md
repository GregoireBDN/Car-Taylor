# Car Taylor - Configurateur de Voiture

## Contexte Académique

Projet réalisé dans le cadre de l'UE Architecture Logicielle du Master 1 Ingénierie Logicielle à l'ISTIC encadré par M. Noel Plouzeau (Université de Rennes 1).

**Auteurs :**

- Grégoire Bodin
- Léo Bernard-Bodier

## Description

Car Taylor est une application Java qui implémente un configurateur de voiture intelligent avec une interface graphique JavaFX. Le projet met en œuvre les principes d'architecture logicielle et de conception orientée objet.

## Fonctionnalités Principales

### 1. Configuration de Véhicule

- Sélection intuitive des pièces par catégorie
- Validation en temps réel des choix
- Indication visuelle de la validité de la configuration
- Réinitialisation de la configuration
- Feedback détaillé sur les incompatibilités

### 2. Gestion des Compatibilités

- Interface dédiée pour gérer les règles de compatibilité
- Ajout/suppression d'incompatibilités entre pièces
- Gestion des dépendances (nécessités) entre pièces
- Mise à jour en temps réel des règles
- Validation automatique des configurations

### 3. Panneau de Résumé

- Vue d'ensemble des compatibilités et nécessités
- Organisation par catégories
- Affichage clair des incompatibilités et dépendances
- Mise à jour dynamique lors des modifications

### 4. Interface Utilisateur

- Navigation par onglets
- Interface responsive avec barres de défilement
- Retour visuel sur l'état de la configuration
- Messages d'erreur explicites
- Design moderne et ergonomique

### 5. Gestion des Données

- Chargement des catalogues depuis des fichiers JSON
- Structure de données optimisée
- Validation des données en temps réel
- Persistance des configurations

## Architecture Technique

### Technologies Utilisées

- Java 11+
- JavaFX pour l'interface graphique
- Jackson pour le parsing JSON
- JUnit pour les tests unitaires
- Maven pour la gestion de projet

### Structure du Projet

```
cartaylor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── fr/istic/bodin_bodier/cartaylor/
│   │   │       ├── api/           # Interfaces
│   │   │       ├── impl/          # Implémentations
│   │   │       └── gui/           # Interface graphique
│   │   └── resources/
│   │       └── data/              # Fichiers de configuration
│   └── test/
│       └── java/                  # Tests unitaires
```

## Installation et Exécution

### Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur
- IDE recommandé : IntelliJ IDEA ou Eclipse

### Installation

```bash
git clone https://github.com/tony-bodin/cartaylor.git
cd cartaylor
mvn clean install
```

### Exécution

```bash
mvn javafx:run
```

### Tests

Le projet inclut des tests unitaires complets. Pour les exécuter :

```bash
mvn test
```

## Principes d'Architecture

- **Séparation des préoccupations** : API/Implémentation/GUI
- **Design Patterns** : Observer, Factory, Singleton
- **SOLID Principles** : Single Responsibility, Open/Closed, etc.
- **Clean Architecture** : Découpage en couches distinctes
- **Dependency Injection** : Couplage faible entre composants

## Documentation

La documentation complète du projet est disponible dans le dossier `docs/` et inclut :

- Instructions pour réaliser le projet
- Javadoc
- Diagrammes UML

## Documentation

La documentation complète du projet est disponible via JavaDoc. Pour la générer :

```bash
mvn javadoc:javadoc
```

La documentation sera générée dans `target/site/apidocs/`
