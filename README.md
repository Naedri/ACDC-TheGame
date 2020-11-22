# ACDC-TheGame

Un arbitre, une IA et une IHM pour *The game*.

IMT - FIL1 - 2020

*JALLAIS Adrien : adrien.jallais@protonmail.com*

**Partie 1 - Version 1.0**

## Introduction

Dans le cadre du cours Actualisation des Compétences en Développement et Conception (ACDC), Rémi Douence a proposé de développer une application basée sur le jeu *The Game*.

### Objectifs

Comme le décrivent les consignes disponibles dans au document suivant : [presentation-projet-eleves-2020-2021.pdf](./Consignes/presentation-projet-eleves-2020-2021.pdf), les objectifs pédagogiques de son projet sont les suivants : 

+ Suivre l'évolution de la conception d'un projet logiciel ;
+ Favoriser la programmation orientée objet ;
+ Appréhender l'utilisation de code réalisé par les ressources métiers pour la réalisation d'une interface graphique.

### Utilisation de l'application 

Les fichiers sources sont accessibles au dossier suivant : [Code/FIL A1 ACDC Partie1 Jallais Adrien](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src).
Pour savoir comment lancer l'application, reportez-vous au fichier suivant : [module-info.java](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src/module-info.java).

### Règles

Les règles du jeu sont disponibles au fichier suivant : [the-game-english](./Consignes/the-game-english.pdf).

## Résultats

### Aperçu

L'*Illustration 1* présente ce qu'affiche la console lors du lancement d'une partie.

![Capture console au lancement d'une partie](./Illustrations/Capture_1.PNG)

<u>Illustration 1 :</u> Capture de la console au lancement d'une partie.

### Dimension linguistique

Dans le but de maintenir un langage homogène de développement avec celui des règles fournies, et de faciliter un transfert du code entre système Unix et Windows, il a été favorisé l'anglais.

Une traduction des éléments du jeu est fournie ci-dessous afin de faciliter l'utilisation et la lecture du code de l'application :

+ *Pile*
  + *LayPile* = pile de dépôt
    + *Descending pile* = pile de dépôt descendante
    + *Ascending pile* = pile de dépôt ascendante  
  + *DrawPile* = pioche
  + *Hand* = cartes en main
+ *Backwards* (BW) *trick* = action de poser une carte sur une *LayPile*, dont la valeur est +/-=10 à la valeur de la dernière carte posée.
+ *Rules* = règles du jeu

### Progression et suivi du projet

Un fichier décrivant les logs quotidiens réalisés est disponible au fichier suivant : [log.Jallais.Adrien.json](./log.Jallais.Adrien.json). En complément, le *Tableau 1* illustre ces logs pour mieux visualiser la cinétique de développement du projet. 

![Tableau de progression](./Grille_progression/avt.Jallais.Adrien.jpg)
<u>Tableau 1 :</u> Grille de progression du développement de l'application. Les logs représentant un jour de travail.

Avec le *Tableau 1*, on observe que l'écriture des interfaces a permis de guider le développement des classes concrètes les implémentant, et que le développement a commencé par les objets les plus basiques (Cartes, Piles) pour servir de base à des objets intermédiaires (Fabriques), ou plus complexes (Jeu puis Service de Résolution). 

### Diagramme de classe

Les diagrammes page suivante, présentent une organisation ascendante des différentes parties de
cette application. On y observe en amont des interfaces, permettant le lancement des applications
par l'Appli.java. Celles-ci sont implémentées par des classes abstraites dont CA_Grille_Partie
définissant le déroulement commun des parties. C'est cette dernière que les parties concrétisent.

L' *Illustration 1* est un diagramme de classe UM généré avec [ObjectAid UML Explorer](https://objectaid.com/home). Les relations entre ses entités étant ajoutées de manière automatique, il est rapidement devenu surchargé et illisible, comme le montre sa [version brute](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src/FIL_A1_ACDC_Partie1_Jallais_Adrien-UML-Vraw.png). 
En effet, afin d'améliorer sa lisibilité, les caractéristiques suivantes ne sont pas montrées :

+ les relations de dépendance entre les classes (au profit de celles entre les packages),
+ les méthodes de visibilité publique des classes implémentant des interfaces (afin d'éviter une répétition entre ces deux entités).

![Diagramme de classe en version légère](./Code/FIL%20A1%20ACDC%20Partie1%20Jallais%20Adrien/src/FIL_A1_ACDC_Partie1_Jallais_Adrien-UML-Vlight.png)

<u>Illustration 2 :</u>  Diagramme UML de classe de l'application.

L'*Illustration 2* présente la composition de notre application. Les packages ont été divisés dans le but de rassembler des fonctionnalités communes et/ou des classes au degré de complexité commun et/ou des objets strictement indépendants. 

Ainsi, les méthodes statiques ayant pour but de fournir un service, pour l'interaction avec l'utilisateur (*ServiceUser*),  pour la formalisation des règles du jeu (*ServiceRules*) et pour la résolution du jeu (*ServiceResolution*), sont regroupés dans le package *services*. Les classes suivant le patron de conception Fabrique, ont été regroupées dans le package *fabrique*. 
Ainsi, avec un degré de complexité croissant, on note les packages suivants : *card*, *pile* et *game*. 
Ainsi, la classe d'énumération *Direction* et la classe *App* sont dans des packages indépendants : *direction* et *app* , respectivement. 

Les classes concrètes (comme *GameFactory*, *Game*, *DrawPileFactory*, *DrawPile*, *Hand*) n'ont pas de relations d'association avec d'autres classes concrètes, en effet celles-ci sont associées à des interfaces, et ces dernières sont implémentées par les classes concrètes correspondantes. 

Les Fabriques permettent de sécuriser la création de la pile de pioche (*DrawPile*) et du jeu (*Game*), avec *DrawPileFactory*. En effet, selon les règles données par *ServicesRules*, les fabriques vérifient notamment la validité de la pioche fournie (comme l'intervalle des cartes autorisées) et règlent la composition concrète d'un jeu (comme le nombre de *Descending* et *Ascending Pile*).

Les méthodes de *ServiceResolution* sont statistiques, et ne sont pas dans le même package que celui de la classe *Game* afin d'inciter sur la résolution d'une partie selon le principe d'une API ([Interface de programmation](https://fr.wikipedia.org/wiki/Interface_de_programmation)).

Les classes *LayInfo* et *Move* du package *game* permettent de présenter des informations à l'utilisateur. 
En effet, *LayInfo* présente l'état des piles de dépôt (*LayPile*) et leur indice, afin de permettre à l'utilisateur de les distinguer dans une partie. 
En effet, *Move* représente un coup (c'est à dire l'association de l'indice d'une carte et de l'indice d'une pile de dépôt), la succession d'instance de cette classe permet de représenter les résultat du service de résolution du jeu (*ServiceResolution*).

La classe *Enumeration* permet de donner un sens, croissant ou décroissant, de dépôt des cartes sur les piles de dépôt (*LayPile*). Elle permet également de régler la valeur qui sépare deux cartes pour réaliser un coup BW.

### Fonctionnement du service de résolution du jeu

Le service de résolution du jeu (IA) propose un succession de coups (couple d'indices de pile du jeu et de carte en main) à jouer pour tenter de gagner une partie.
Pour cela, l'IA associe un poids à chacun de ces couples, et sélectionne le plus faibles de ceux-ci. Dans le cas où deux couples auraient le même poids on sélectionne le couple pour lequel la pioche a l'intervalle de cartes disponibles le plus grand.

Le poids de ce couple augmente proportionnellement à l'écart entre la valeur de la carte de la pioche et la valeur de la carte de la main. L'IA recherche donc à poser des cartes dont la valeur est au plus proche de la dernière carte des piles de dépôt. Lorsqu'il y a la possibilité de réaliser un coup BW avec la carte de la pioche celui est favorisé puisque le poids associé est inférieur à 0.
Ce processus est réalisé par la méthode suivante : *ServiceResolution.chooseOneLayOneCard*.

Si l'on minimise au maximum les coups à l'échelle d'un tour (état en *log 9*), la fonction *ServiceResolution.resolve*  fournit des indications qui permettent d'arriver au résultat suivant :
```
IA has layed 39 cards.
The Game won.
```

Dans le but d'améliorer ce résultat, il a été développer une fonctionnalité qui permet d'indiquer quelle carte poser pour favoriser la mise en place d'un coup BW avec deux cartes de sa main.
Ce processus est réalisé par la méthode suivante : *ServiceResolution.chooseOneLayOneCombination*.

Avec l'utilisation de cette nouvelle fonctionnalité (état en *log 10*), la fonction *ServiceResolution.resolve*  fournit désormais des indications qui permettent d'arriver au résultat suivant : 
```
IA has layed 48 cards.
The Game won.
```

## Discussions

### Choix réalisés

#### Collections.unmodifiableSet()

##### Problème

Les tableaux sont des données non immuables.
Ainsi lorsque l on va passer une carte à un joueur il va pouvoir la modifier. En effet avec un get on lui donne la référence de notre objet.

##### Options

1. duplication des cartes (copie en locale) pour comparaison des cartes données et reçues = [copie défensives stackoverflow](https://stackoverflow.com/questions/15020850/copy-constructors-and-defensive-copying) ou [how to copy an object in java](https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java) ou [unmodifiableMap  stackoverflow](https://stackoverflow.com/questions/18141234/should-defensive-copies-always-be-made)-> [Collections.unmodifiableSet](https://www.geeksforgeeks.org/collections-unmodifiableset-method-in-java-with-examples/)
2. liste immuable
3. ? observation en fin de jeu des cartes ?
4. comme configuration 1 joueur, l'ensemble des cartes formé par {*LayPile*, *DrawPile*, *Hand*} est un ensemble complet des cartes disponibles -> on peut donc comparer ce qu il y a dans les *LayPile* et la *DrawPile* pour savoir si *Hand* ne donne pas de cartes redondantes.
5. utiliser *final int*  ou *Integer* pour les numéros de card ? 

[references sur copie defensive](https://code.i-harness.com/fr/q/d42a9)

##### Décisions

[java.util.Collections.unmodifiableCollection() ](https://www.tutorialspoint.com/java/util/collections_unmodifiablecollection.htm)

#### Minimser le nbr de cartes posées ?

##### Problème

Il est possible de garder en reserve un **couple** de cartes permettant de réaliser un draw back, afin de maximiser le dépot de carte >/< à la première carte de se couple, récupéré après une action de pioche.
Cela est peut *risqué* si l on est seul car on maitrise quel sont les prochaines cartes posées, cependant à plusieurs ce n'est plus le cas.

##### Options

+ si oui
  + toujours proche de 2 
  + plus optimal quand joueur seul
+ si non
  + maximiser les sauts/ combinaisons
  + plus optimal quand X joueurs

##### Décision

Options : 
1) Spécialiser IA fonction nombre de joueur
2) piocher à chaque fin de tour 

#### Poser Min(Diff(cartes)) ou Diff(10) ?

10 points pour augmenter la marge

#### find duplicate for DrawPile

duplication number wll be check in the factory

#### CAPile

factoring some method on its colletion card ?
but we can not specialized its attribute in queue or 

#### Comment piocher et déposer de sa main ?
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

#### List = ArrayList or Linked List

linked list for one Laying Pile as we do not need to sort them
ArrayList for one hand as we need to sort them

the group of hands and the groupe of LayingPile need to be sorted thus ArrayList

#### comment choisir une laypile ? avec direction + number ou juste number ?
méthodes au non differentes suivant asc et desc et un nombre

#### Externalisation des paramètres orchestrant les règles du jeu



#### Externalisation de la méthode de résolution


### Bilan de l'application

#### Points faibles 

+ IA qui ne fonctionne pas assez bien : ne visualise pas les combinaisons de drawback entre deux cartes de sa main :  Seulement entre une carte de sa main et une carte de la pile.

#### Points forts

+ D'après les tests, la fabrique de pile est capable d'identifier un jeu de données falsifiés.
+ D'après les tests, les services IA fonctionnent tout de même.
+ Les acteurs du jeu sont des piles ce qui permet d'optimiser le fonctionnement de l'application.

+ Il est possible de modifier facilement un certains nombre de paramètres
	+  différence de valeur entre deux cartes interposées dans une pile,
	+ Paramètres de nombre de joueur et de grandeur de pile sont définis en un seul fichier :
		+ ce qui permet de jouer avec une pioche d'un nombre restreint  de cartes, comme 20.

+ Les classes utilisent des méthodes définies au sein d'interfaces et non au sein de classes concrètes 
	+ ce qui permettra une modification plus rapide et légère de l'implémentation des méthodes si  nécessaire.
+ les patrons de conception ont été implémentés:
	+ singleton
	+ fabrique  