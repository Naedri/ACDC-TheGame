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

### find duplicate for DrawPile

duplication number wll be check in the factory

### CAPile

factoring some method on its colletion card ?
but we can not specialized its attribute in queue or 

### Comment piocher et déposer de sa main ?
répartir la pioche et la déposer dans les classes IDrawPile et ILAyPile respectivement
mettre en protected les fonction piocher et déposer

```
	// CAUTION
	public void addCard(ICard card);
```

#### comment gérer le flux des cartes entre 2 acteurs ?

Doit on avoir une méthode dans chacune des classes qui donnent un résultat similaire ? et risquer de ne pas avoir le même resultat alors qu on veut la même chose ?
doit on choisir une seule des deux classes impliquées ? mais la quelle ? la receveuse ou la donneuse ? 

+ la donneuse puisqu elle est en amont
+ mais comment controle que la donneuse donne au bon endroit ? la donneuse n'a pas à le savoir, mais le receuveur ne pas controler ce choix non plus -> il faut un 3ème acteur un mediateur
+ ce mediateur ne doit pas être dans un package différent que les acteurs eux mêmes, puisqu on va utiliser des méthodes protected ... pourquoi utiliser des méthodes protected ??? 
+ ce mediateur sera la classe *Game* et les classes donnantes et receveuse devront présenté des méthodes public donnantes receveuse

### List = ArrayList or Linked List

linked list for one Laying Pile as we do not need to sort them
ArrayList for one hand as we need to sort them

the group of hands and the groupe of LayingPile need to be sorted thus ArrayList

### comment choisir une laypile ? avec direction + number ou juste number ?
méthodes au non differentes suivant asc et desc et un nombre

## Draft code

### shallow and deep copying and immuable attributes 

```java
		/* POINT */
		// https://stackoverflow.com/questions/15020850/copy-constructors-and-defensive-copying
		// A simple point.
		Point p1 = new Point(3, 42);
		// A new point at the same place as p1 but a completely different object.
		Point p2 = new Point(p1);
		p2.x = 5;

		System.out.println(p1.toString());

		System.out.println(p1.x);
		System.out.println(p2.toString());
		System.out.println(p2.x);

		/* DUMMY */
		// https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java
		DummyBean dum = new DummyBean();
		dum.setDummy("foo");
		System.out.println(dum.getDummy()); // prints 'foo'

		// DummyBean dumtwo = dum; // prints 'bar' but it should print 'foo'
		DummyBean dumtwo = new DummyBean(dum); // prints 'bar'
		System.out.println(dumtwo.getDummy()); // prints 'foo'

		dum.setDummy("bar");
		System.out.println(dumtwo.getDummy()); // prints 'bar' but it should print 'foo'

```