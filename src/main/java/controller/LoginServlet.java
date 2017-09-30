package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.UserBO;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String USER_SESSION = "userSession";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// if(!command.equals("login")) return;
		UserBO _DAO = new UserBO();
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String check = "";
		
		User item;
		// check user
		item = _DAO.checkEmailUser(user);
		if (item == null) {
			response.getWriter().print("user");
			return;
		}
		// check pass
		item = _DAO.checkLogin(user, pass);
		if (item == null) {
			response.getWriter().print("pass");
			return;
		} else {
			if (item.getRoles() == 0) {
				response.getWriter().print("active");
				return;
			}

			// set cookie login
			// Create cookies for first and last names.
			Cookie cuser = new Cookie("username", user);
			Cookie cpass = new Cookie("password", pass);
			System.out.println(check+"check nè");
			// Set expiry date after 24 Hrs for both the cookies.
			cuser.setMaxAge(60 * 60 * 24);
			cpass.setMaxAge(60 * 60 * 24);
			response.addCookie(cuser);
			response.addCookie(cpass);

			request.getSession().setAttribute(LoginServlet.USER_SESSION, item);
			response.getWriter().print("success");
		}

	}

}
