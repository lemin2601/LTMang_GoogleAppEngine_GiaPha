package com.example.appengine.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.dao.PeopleDAO;

/**
 * Servlet implementation class DeletePeopleServlet
 */
@WebServlet("/DeletePeopleServlet")
public class DeletePeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePeopleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
		String id = request.getParameter("id_people");
		PeopleDAO _DAO = new PeopleDAO();
		int result = _DAO.deleteAllBranch(Long.parseLong(id));
		result +=_DAO.deleteItem(Long.parseLong(id));
		if(result > 0){
			response.getWriter().println("success");
		}else{
			response.getWriter().println("failed");
		}
	}

}
