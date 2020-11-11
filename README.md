# ACDC-TheGame

Un arbitre, une IA et une IHM pour The game

IMT - FIL1 - 2020

## Présentation

Dans le cadre du cours de Web, Rémi Douence a proposé de développer une application basée sur le jeu *The Game*.

### Règles

### Traduction

Dans le but de maintenir un langage homogène de développement avec celui des règles founies, il a été favorisé l'anglais.  
Pour respecter l'uniformité du code fourni au sein du groupe de travail, il sera envisagé que les méthodes de l'interface API soit rédigées en français.  
Néanmoins une traduction est fournie ci-dessous afin de faciliter la lecture de code :

+ *Pile*
  + *LayPile* = pile de dépôt
    + *Descending pile* = pile de dépôt descendante
    + *Ascending pile* = pile de dépôt ascendante  
  + *DrawPile* = pioche
  + *Hand* = cartes en main
+ *backwards* = action de poser une carte sur une LayPile +/-=10 à la valeur de sa dernière carte (dans le but de tenter de redéposer des cartes non posées)

## Auteur

JALLAIS Adrien : adrien.jallais@protonmail.com

## Informations générales

### Objectifs

+ Favoriser la programmation orientée objet.
+ Appréhender l'utilisation de code réalisé par les ressources métiers pour la réalisation d'une interface graphique.

### Diagramme de classe

![Diagramme de classe](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/bin/FIL_A1_ACDC_Partie1_Jallais_Adrien-UML.png)

## Choix réalisés

### 1

#### Problème

Les tableaux sont des données non immuables.
Ainsi lorsque l on va passer une carte à un joueur il va pouvoir la modifier. En effet avec un get on lui donne la référence de notre objet.

#### Options

1. duplication des cartes (copie en locale) pour comparaison des cartes données et reçues = [copie défensives stackoverflow](https://stackoverflow.com/questions/15020850/copy-constructors-and-defensive-copying) ou [how to copy an object in java](https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java) ou [unmodifiableMap  stackoverflow](https://stackoverflow.com/questions/18141234/should-defensive-copies-always-be-made)-> [Collections.unmodifiableSet](https://www.geeksforgeeks.org/collections-unmodifiableset-method-in-java-with-examples/)
2. liste immuable
3. ? observation en fin de jeu des cartes ?
4. comme configuration 1 joueur, l'ensemble des cartes formé par {*LayPile*, *DrawPile*, *Hand*} est un ensemble complet des cartes disponibles -> on peut donc comparer ce qu il y a dans les *LayPile* et la *DrawPile* pour savoir si *Hand* ne donne pas de cartes redondantes.
5. utiliser *final int*  ou *Integer* pour les numéros de card ? 

[references sur copie defensive](https://code.i-harness.com/fr/q/d42a9)

#### Décisions

A définir

### Minimser le nbr de cartes posées ?

#### Problème

Il est possible de garder en reserve un **couple** de cartes permettant de réaliser un draw back, afin de maximiser le dépot de carte >/< à la première carte de se couple, récupéré après une action de pioche.
Cela est peut *risqué* si l on est seul car on maitrise quel sont les prochaines cartes posées, cependant à plusieurs ce n'est plus le cas.

#### Options

+ si oui
  + toujours proche de 2 
  + plus optimal quand joueur seul
+ si non
  + maximiser les sauts/ combinaisons
  + plus optimal quand X joueurs

#### Décision

Spécialiser IA fonction nombre de joueur

### Poser Min(Diff(cartes)) ou Diff(10)

#### Décision

10 points pour augmenter la marge