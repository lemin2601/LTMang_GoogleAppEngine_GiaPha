package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GenealogyDAO;
import model.Genealogy;

/**
 * Servlet implementation class DeleteGenealogySeverlet
 */
@WebServlet("/DeleteGenealogySeverlet")
public class DeleteGenealogySeverlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGenealogySeverlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
        String command = request.getParameter("command");
        if (!command.equals("delete_genealogy")) {
            return;
        }
        String id = request.getParameter("id"); 
        GenealogyDAO _DAO = new GenealogyDAO();		
        Genealogy item = new Genealogy();
        item.setId(Long.parseLong(id));  
        if(_DAO.deleteItem(item)>0){
        	 response.getWriter().print("success");
        } else {
            response.getWriter().print("failed");
        }
	}

}
