package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.GestionEtudians;
import models.Etudiant;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registration() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String filere = request.getParameter("filiere");
		
		Etudiant etud = new Etudiant();
		etud.setNom(nom);
		etud.setPrenom(prenom);
		etud.setLogin(login);
		etud.setPassword(password);
		etud.setFiliere(filere);

		if (GestionEtudians.register(etud) == true) {
			HttpSession ss = request.getSession();
			ss.setAttribute("etudiant", etud);
			response.sendRedirect("user_home.jsp");
		}
		else {
			out.println("Does Not Exist");

		}
	}

}
