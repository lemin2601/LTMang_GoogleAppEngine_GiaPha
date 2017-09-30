package com.example.appengine.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GenealogyDAO;
import model.Genealogy;

/**
 * Servlet implementation class EditGenealogyServlet
 */
@WebServlet("/EditGenealogyServlet")
public class EditGenealogyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 GenealogyDAO _DAO;
    public EditGenealogyServlet() {
        super();
        _DAO= new GenealogyDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("public/index,jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
        String command = request.getParameter("command");
        if (!command.equals("edit_genealogy")) {
            return;
        }
        String id = request.getParameter("id"); 
        
		String name = request.getParameter("name");
        String description = request.getParameter("description");
        Genealogy item = new Genealogy();
        item.setId(Long.parseLong(id));
        item.setName(name);
        item.setDescription(description);       
        if(_DAO.editItem(item)>0){
        	 response.getWriter().print("success");
        } else {
            response.getWriter().print("failed");
        }
	}

}
