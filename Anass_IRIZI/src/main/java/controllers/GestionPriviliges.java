package controllers;

import dao.AdminDAO;

public class GestionPriviliges {
	
	public static boolean isAdmin(String login, String password) {
		if (AdminDAO.checkAdmin(login, password) == null) {
			return false;
		}

		return true;
	}

}
