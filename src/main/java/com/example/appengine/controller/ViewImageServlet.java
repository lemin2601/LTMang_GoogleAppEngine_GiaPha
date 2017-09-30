package com.example.appengine.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PeopleDAO;

/**
 * Servlet implementation class ViewImageServlet
 */
@WebServlet("/ViewImageServlet")
public class ViewImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("img");
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
		PeopleDAO _DAO = new PeopleDAO();
		ByteArrayOutputStream baos = _DAO.getImage(Long.parseLong(id));
		byte[] bytes = baos.toByteArray();
		if (bytes != null && bytes.length > 0) {
			response.setContentType("image/jpg");
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
