package com.example.appengine.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.dao.GenealogyDAO;
import com.example.appengine.model.Genealogy;
import com.example.appengine.model.User;

/**
 * Servlet implementation class AddGenealogyServlet
 */
@WebServlet("/AddGenealogyServlet")
public class AddGenealogyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    GenealogyDAO _DAO;
    public AddGenealogyServlet() {
        super();
        _DAO= new GenealogyDAO();
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
		response.setContentType("text/html;charset=UTF-8");
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
        String command = request.getParameter("command");
        if (!command.equals("create_genealogy")) {
            return;
        }
       
		String name = request.getParameter("name");
        String description = request.getParameter("description");
        Genealogy item = new Genealogy();
        item.setId(System.currentTimeMillis());
        item.setName(name);
        item.setDescription(description);
        
        User user =(User) request.getSession().getAttribute(LoginServlet.USER_SESSION);
        if(_DAO.addItem(item,user.getId())>0){
        	 response.getWriter().print("success");

        } else {
            response.getWriter().print("failed");
        }
        
	}

}
