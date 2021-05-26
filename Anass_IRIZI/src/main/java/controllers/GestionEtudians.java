package controllers;


import dao.EtudiantDAO;

import models.Etudiant;

public class GestionEtudians {
	
	// Inscription
	public static boolean register(Etudiant etud) {
		// if registering the new student failed
		// then return false
		if (!EtudiantDAO.addEtudiant(etud)) {
			return false;
		}
		
		// else return true
		return true;
	}

	// Authentification
	public static Etudiant connect(String login, String password) {
		// if the student does exist then an object that contains all the attributes
		// of this particular student
		// else null will be returned
		return EtudiantDAO.checkStudent(login, password);
	}
}
