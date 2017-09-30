package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.UserBO;
import model.User;

/**
 * Servlet implementation class RegiseterServlet
 */
@WebServlet("/RegisterServlet")
public class RegiseterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegiseterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBO _DAO = new UserBO();
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		User item;
		// check email
		item = _DAO.checkEmail(email);
		if (item != null) {
			response.getWriter().print("email");
			return;
		}
		// check user
		item = _DAO.checkUser(user);
		if (item != null) {
			response.getWriter().print("user");
			return;
		}
		item = new User(System.currentTimeMillis(), user, email, pass, 1);
		// check pass
		if (_DAO.addItem(item) > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("failed");
		}
	}

}
