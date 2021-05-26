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
import controllers.GestionPriviliges;
import models.Etudiant;

@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connection() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Etudiant user = GestionEtudians.connect(login, password);
		if (user != null) {
			HttpSession ss = request.getSession();
			
			if (GestionPriviliges.isAdmin(user.getLogin(), user.getPassword())) {
				ss.setAttribute("admin", user);
				response.sendRedirect("admin_home.jsp");
			} else {
				ss.setAttribute("etudiant", user);
				response.sendRedirect("user_home.jsp");
			}

		} else {

			request.setAttribute("ERROR", "Login ou Mot de passe incorrecte");
			request.getRequestDispatcher("connect.jsp").forward(request, response);
		}
	}

}
