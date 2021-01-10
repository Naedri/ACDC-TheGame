# ACDC-TheGame

Un arbitre, une IA et une IHM pour *The game*.

IMT - FIL1 - 2020

*JALLAIS Adrien : adrien.jallais@protonmail.com*

__*Partie 2 - Version 1.0*__

## Introduction

### Utilisation de l'application

Les fichiers sources sont accessibles dans le dossier suivant : [Code/FIL A1 ACDC Partie2 Jallais Adrien](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src).
Pour savoir comment lancer l'application, reportez-vous au fichier suivant : [module-info.java](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/module-info.java).

### Auteurs

La dimension *frontend* ou Interface Homme Machine (IHM) a été réalisée par Adrien Jallais. Cette IHM utilise une API réalisée par Nicolas Kirchhoffer, à laquelle il a été apporté quelques modifications par Adrien Jallais. 

## Résultats

### Aperçu

L'*Illustration 1-1* présente ce que la fenêtre cliente affiche au cours d'une partie en **mode Solo**.

![Capture fenêtre en mode Solo](../Illustrations/Capture_2-Play-Human.PNG)

*__Illustration 1-1 :__ Capture de la fenêtre au cours d'une partie en mode Solo.*

L'*Illustration 1-2* présente ce que la fenêtre cliente affiche au départ d'une partie en **mode Démonstration**.

![Capture fenêtre en mode Démonstration](../Illustrations/Capture_2-Play-IA.PNG)

*__Illustration 1-2 :__ Capture de la fenêtre au départ d'une partie en mode Démonstration.*

### Progression et suivi du projet

Un fichier décrivant les logs réalisés quotidiennement est disponible dans le fichier suivant : [log.Jallais.Adrien.json](../log.Jallais.Adrien.json). En complément, le *Tableau 1* illustre ces logs pour mieux visualiser la cinétique de développement du projet.

![Tableau de progression](../Grille_progression/avt.Jallais.Adrien.2.jpg)
*__Tableau 1 :__ Grille de progression du développement de l'application. Les logs représentent un jour de travail.*

Avec le *Tableau 1*, on observe que les premières  scènes qui ont été mises en place sont celles qui demandaient le moins de complexité. En effet, dans le but de monter en compétence de manière graduelle avec la librairie JavaFX les scènes d'accueil et du menu ont d'abord été réalisées car elles comportaient une infrastructure simple : un à deux composants d'agencement impliqués, et des composants interactifs basiques (bouton) avec des actions similaires (changement de scène).
La réalisation de ces scènes a permis également de poser les bases d'un design homogène de cette application. En effet il a été mis en évidence la nécessité d'un *wrapper* commun aux scènes pour plus d'homogénéité entre elles, ainsi que la mise en place de constantes communes, pour un style commun entre les scènes, qui possèdent notamment des boutons et les labels identiques. 

### Diagramme de classe

L' *Illustration 2* est un diagramme de classe UML généré avec [ObjectAid UML Explorer](https://objectaid.com/home). Les relations entre ses entités étant ajoutées de manière automatique, il est rapidement devenu surchargé et illisible.
Afin d'améliorer sa lisibilité, les caractéristiques suivantes ne sont pas montrées :

+ les relations de dépendance entre les classes (au profit de celles entre les packages),
+ les méthodes de visibilité publique des classes implémentant des interfaces (afin d'éviter une répétition entre ces deux entités),
+ les méthodes des classes du package [view.scene](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view), qui consistent à créer les constituant d'un *BorderPane* de JavaFX,
+ les classes du package [api](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/api).

![Diagramme de classe en version légère](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/FIL_A1_ACDC_Partie2_Jallais_Adrien-UML-Vlight.jpg)

*__Illustration 2 :__  Diagramme UML de classe de l'application.*

L'*Illustration 2* présente la composition de notre application. 

Les composants de jeu de l'application sont rassemblés au sein du package [view.component](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/component), afin d'optimiser leur réutilisation au sein des scènes de type *APlayScene*. Pour les classes concrètes de ce package

+ [view.label](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/label),
+ [view.exception](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/exception), qui contient les classes permettant de 
+ [view.constant](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/constant),
+ [view.button](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/button),
+ [view.scene](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/scene),
+ [view.component](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/view/component), 
+ [nls](../Code/FIL%20A1%20ACDC%20Partie2%20Jallais%20Adrien/src/nls)



## Discussion 

### Analyse du code fourni

#### Point négatifs du code fourni

carteIA constructeur avec indice de tas et pas un ITas

Liste de carte et non une pile -> desavantages de ne pas avoir les methodes associées, par exemple dans la méthode piocher : this.cartes.subList + removeAll alors qu'un pop aurait suffit

/!\ : 
	public List<Carte> getCartes() {
		return this.cartes;
	}
permet d'avoir la refererence de la liste de la carte est on peut l'annuler

a voir comment la victoire est atteinte

#### Choix de la pioche

Pas de factory de pioche, peut on mettre n'importe quel type de text ?
A voir si on peut mettre des nombres en double.

#### Méthode jouer

`public void jouer(int tasId, Carte carte, Joueur joueur)`
arguments non homogènes -> plus facile si on avait juste une valeur de carte à mettre

#### Organisation du code

pas de séparation en package
deux fonctions Main -> un seul devrait être appellé un 

#### Piles de dépôts initiées à 0 et non 1

#### Piocher avec deux méthodes dans le main

partie.passerTour();
joueur.piocher(partie);
le joueur peut il piocher si il a de la place dans sa main un nombre de cartes plus important que celui de la pioche ?

#### Partie terminée mais pas de victoire ou de défaite

On obtient seulement le score et pas la victoire ou non.

#### Factory Pioche

#### Autres

feat(gameAPI): modif de l'api pour definir si la partie est gagnée ou non
feat(gameAPI): modif de l'api pour initier les piles de dépôt non pas à 0 mais à 1
feat(gameAPI): modif api (méthode jouer et isPartieFinie)
feat(gameAPI): modif api (fromFile method vérfiant validité pioche donnée)
feat(gameAPI): modif api (fromFile method pouvant créer pioche aléatoire)
feat(gameAPI): modif API to know the max number cards by user
feat(gameAPI): modif API pour incrémenter le nbr de tour qui restait à 0 car jeu à un seul joueur
fix(gameAPI): IA ne pioche pas si la pioche est vide

#### Service IA

On ne peut obtenir le meilleur coup qu'il faut jouer sans qu'il le soit : pas d'indice possible car il est joué directement.

#### Points positifs  du code fourni

carteIA permettant d'associer un poids à une carte de manière durable

Liste de tas

Joueur IA extends JouerHumain

levée d'exceptions utiles pour l'affichage d'erreur

### Choix réalisés

La méthode 

### Bilan de l'application

#### Points faibles de l'application

Non compatible avec les smartphones

Pas de fonctionnalité drag and drop pour les cartes qui semblent être une fonctionnalité importante pour l'utilisateur

#### Points forts de l'application

Respect du cahier des charges, 

changement de langue possible,

IHM basée sur l'utilisation de composants réutilisable,

Les composants ont un design homogène entre eux car celui est basé sur des énumérations.

Pas d'utilisation de feuille css, ainsi l'utilisation de variables est possible pour l'affinement du style



