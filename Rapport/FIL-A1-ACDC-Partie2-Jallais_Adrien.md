# ACDC-TheGame

Un arbitre, une IA et une IHM pour *The game*.

IMT - FIL1 - 2020

*JALLAIS Adrien : adrien.jallais@protonmail.com*

__*Partie 2 - Version 1.0*__

## Introduction

## Analyse du code fourni

### Point négatifs

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

### Points positifs

carteIA permettant d'associer un poids à une carte de manière durable

Liste de tas

Joueur IA extends JouerHumain

levée d'exceptions utiles pour l'affichage d'erreur