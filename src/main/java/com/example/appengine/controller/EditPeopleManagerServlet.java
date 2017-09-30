package com.example.appengine.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.DateUtil;
import dao.PeopleDAO;
import model.People;

/**
 * Servlet implementation class EditPeopleManagerServlet
 */
@WebServlet("/EditPeopleManagerServlet")
public class EditPeopleManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPeopleManagerServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String birthday = request.getParameter("birthday");
		String deadday = request.getParameter("deadday");
		PeopleDAO dao = new PeopleDAO();
		try {
			People p = new People(Long.parseLong(id), firstname, lastname, DateUtil.convetToDate(birthday), DateUtil.convetToDate(deadday), Integer.parseInt(sex), address);
			dao.editItem(p);
			request.setAttribute("msg", "Update successful!");
			ArrayList<People> list = new ArrayList<>();
			list = dao.getList();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/PeopleManager.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
