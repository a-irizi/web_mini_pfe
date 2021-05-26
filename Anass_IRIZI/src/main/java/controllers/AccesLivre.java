package controllers;

import java.util.ArrayList;
import java.util.List;

import tools.DBInteraction;

import models.Livre;
import models.Auteur;

import dao.CategorieDAO;
import dao.LivreDAO;


public class AccesLivre {

	public static List<Livre> listBooksByCategory(String categorie) {
		List<Livre> temp = LivreDAO.getAllLivres();
		List<Livre> lvc = new ArrayList<Livre>();		

		for (Livre livre : temp) {
			if (livre.getCategorie().equals(categorie)) {
				lvc.add(livre);
			}
		}
		
		DBInteraction.disconnect();
		
		return lvc;
	}

	public static List<Livre> listBooksByCategory(int categorie_id) {
		List<Livre> temp = LivreDAO.getAllLivres();
		List<Livre> lvc = new ArrayList<Livre>();		

		for (Livre livre : temp) {
			if (CategorieDAO.checkCategorie(livre.getCategorie()).getCategorie_id() == categorie_id) {
				lvc.add(livre);
			}
		}
		
		DBInteraction.disconnect();
		
		return lvc;
	}

	public static List<Livre> listBooksByAuthor(int auteur_id) {
		List<Livre> temp = LivreDAO.getAllLivres();
		List<Livre> lvc = new ArrayList<Livre>();		

		for (Livre livre : temp) {
			for (Auteur auteur : livre.getAuteurs()) {
				if (auteur.getAuteur_id() == auteur_id) {
					lvc.add(livre);
					break;
				}
			}
		}
		
		return lvc;
	}

	public static List<Livre> listBooksByAuthor(String nom, String prenom) {
		List<Livre> temp = LivreDAO.getAllLivres();
		List<Livre> lvc = new ArrayList<Livre>();		

		for (Livre livre : temp) {
			for (Auteur auteur : livre.getAuteurs()) {
				if (auteur.getNom().equals(nom) && auteur.getNom().equals(prenom)){
					lvc.add(livre);
					break;
				}
			}
		}
		
		return lvc;
	}
}