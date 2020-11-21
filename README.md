# ACDC-TheGame

Un arbitre, une IA et une IHM pour *The game*.

IMT - FIL1 - 2020

*JALLAIS Adrien : adrien.jallais@protonmail.com*

**Partie 1 - Version 1.0**

## Présentation

Dans le cadre du cours de Web, Rémi Douence a proposé de développer une application basée sur le jeu *The Game*.

### Règles

Les règles du jeu sont disponibles au fichier suivant : [the-game-english](./Consignes/the-game-english.pdf).

### Utilisation de l'application 

Les fichiers sources sont accessibles au dossier suivant : [Code/FIL A1 ACDC Partie1 Jallais Adrien](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src).
Pour savoir comment lancer l'application, reportez-vous au fichier suivant : [module-info.java](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src/module-info.java).

### Traduction

Dans le but de maintenir un langage homogène de développement avec celui des règles fournies, il a été favorisé l'anglais.
Pour permettre à des utilisateurs non anglophones de jouer, les dialogues d'interaction lors d'un tour de jeu ont été, pour la plupart, rédigés en français.

Une traduction est fournie ci-dessous afin de faciliter la lecture de code :

+ *Pile*
  + *LayPile* = pile de dépôt
    + *Descending pile* = pile de dépôt descendante
    + *Ascending pile* = pile de dépôt ascendante  
  + *DrawPile* = pioche
  + *Hand* = cartes en main
+ *Backwards* = action de poser une carte sur une *LayPile* +/-=10 à la valeur de sa dernière carte (dans le but de tenter de redéposer des cartes non posées)
+ *Factory* = Fabrique (patron de conception)
+ *Rules* = règles du jeu

## Informations générales

### Objectifs

Comme le décrivent les consignes disponibles dans au document suivant : [presentation-projet-eleves-2020-2021.pdf](./Consignes/presentation-projet-eleves-2020-2021.pdf), les objectifs pédagogiques de son projet sont les suivants : 

+ Favoriser la programmation orientée objet ;
+ Appréhender l'utilisation de code réalisé par les ressources métiers pour la réalisation d'une interface graphique ;
+ Suivre l'évolution de la conception d'un projet logiciel.

### Progression et suivi du projet

Un fichier décrivant les logs réalisés est disponible au fichier suivant : [log.Jallais.Adrien.json](./log.Jallais.Adrien.json). 
La grille de progression ci-dessous, permet d'évaluer la manière dont s'est développé le projet. On y voit, par exemple, que l'écriture des interfaces a permis de guider le développement des classes concrètes les implémentant, et que le développement a commencé par les objets les plus basiques (cartes, piles) pour servir de base à des objets intermédiaires (Fabriques), ou plus complexes (Jeu puis Service de Résolution). 

![Grille de progression](./Grille_progression/avt.Jallais.Adrien.jpg)

## Diagramme de classe

Le diagramme de classe ci dessous est une version légère d'un autre diagramme disponible au fichier suivant [FIL_A1_ACDC_Partie1_Jallais_Adrien-UML-Vraw.png](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src/FIL_A1_ACDC_Partie1_Jallais_Adrien-UML-Vraw.png). En effet, les relations de dépendances entre les objets ne sont pas représentés, exceptées celles pour les packages.

![Diagramme de classe en version légère](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src/FIL_A1_ACDC_Partie1_Jallais_Adrien-UML-Vlight.png)

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

[java.util.Collections.unmodifiableCollection() ](https://www.tutorialspoint.com/java/util/collections_unmodifiablecollection.htm)

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

Options : 
1) Spécialiser IA fonction nombre de joueur
2) piocher à chaque fin de tour 

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

### comment gérer le flux des cartes entre 2 acteurs ?

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

### Externalisation des paramètres orchestrant les règles du jeu



### Externalisation de la méthode de résolution


## Bilan de l'application

### Points faibles 

+ IA qui ne fonctionne pas assez bien : ne visualise pas les combinaisons de drawback entre deux cartes de sa main :  Seulement entre une carte de sa main et une carte de la pile.

### Points forts

+ D'après les tests, la fabrique de pile est capable d'identifier un jeu de données falsifiés.
+ D'après les tests, les services IA fonctionnent tout de même.
+ Les acteurs du jeu sont des piles ce qui permet d'optimiser le fonctionnement de l'application.

+ Il est possible de modifier facilement un certains nombre de paramètres
	+  différence de valeur entre deux cartes interposées dans une pile,
	+ Paramètres de nombre de joueur et de grandeur de pile sont définis en un seul fichier :
		+ ce qui permet de jouer avec une pioche d'un nombre restreint  de cartes, comme 20.

+ Les classes utilisent des méthodes définies au sein d'interfaces et non au sein de classes concrètes 
	+ ce qui permettra une modification plus rapide et légère de l'impléntation des méthodes si  nécessaire.
+ les patrons de conception ont été implémentés:
	+ singleton
	+ fabrique  


## Fonctionnement du service de résolution du jeu

Il faut minimiser au maximum les coups : 
+ A chaque coup on associe un poids et,
+ On prend le coup au poids le plus faible.

chooseOneLayOneCard 
Calculated with chooseOneLay and chooseOneCard. It will act as a tournament.

```java
	 * @param lays
	 * @param hand
	 * @return [ lay_index, card_index ]

	public static int[] chooseOneLayOneCard(List<ILayPile> lays, List<ICard> hand) {


// each lay chooses one card
		// then we calculate move for each, with evalCardLay
		// then we selected the lowest move
// if the new move can be more better
	// we choose the lay with the more place
	
```

Si l'on minimise au maximum les coups à l'échelle d'un tour (etat en log 9), on obtient le résultat suivant :
```
The score is : 2993 / 4949 .
IA has layed 39 cards.
The Game won.
```
Si l'on favorise la réalisation de Backward tricks avec deux cartes de nos mains (état en log 10), on obtient le résultat suivant : 
```
The score is : 2643 / 4949 .
IA has layed 48 cards.
The Game won.
```