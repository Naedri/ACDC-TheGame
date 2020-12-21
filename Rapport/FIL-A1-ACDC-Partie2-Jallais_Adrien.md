# ACDC-TheGame

Un arbitre, une IA et une IHM pour *The game*.

IMT - FIL1 - 2020

*JALLAIS Adrien : adrien.jallais@protonmail.com*

__*Partie 2 - Version 1.0*__

## Introduction

## Analyse du code fourni

### Point négatifs

carteIA constructeur avec indice de tas et pas un ITas
deux fonction Main
Liste de carte et non une pile -> desavantages de ne pas avoir les methodes associées, par exemple dans la méthode piocher : this.cartes.subList + removeAll alors qu'un pop aurait suffit

/!\ : 
	public List<Carte> getCartes() {
		return this.cartes;
	}
permet d'avoir la refererence de la liste de la carte est on peut l'annuler

a voir comment la victoire est atteinte

### Points positifs

carteIA permettant d'associer un poids à une carte de manière durable

Liste de tas

Joueur IA extends JouerHumain