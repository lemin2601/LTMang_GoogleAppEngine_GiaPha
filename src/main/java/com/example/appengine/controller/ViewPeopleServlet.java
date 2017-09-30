package com.example.appengine.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.dao.PeopleDAO;
import com.example.appengine.model.People;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ViewPeopleServlet
 */
@WebServlet("/ViewPeopleServlet")
public class ViewPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPeopleServlet() {
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
		switch (command) {
//		case "get_list":
//			getListGenealogy(request, response);
//			break;
//		case "get_list_by_id":
//			getListPeopleByGenealogyId(request, response);
//			break;
//		case "get_data_view_grid":
//			getDataViewGridByIdGenealogy(request, response);
//			break;
		case "get_item_view_edit":
			getItemPeopleJson(request, response);
			break;
		default:
			break;
		}

	}
	private void getItemPeopleJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PeopleDAO _DAO = new PeopleDAO();
		String id_genealogy = request.getParameter("id_people");
		People item = _DAO.getItem(Long.parseLong(id_genealogy));
		JSONObject data = new JSONObject();
		data.put("id", item.getId());
		data.put("firstname", item.getFirstname());
		data.put("lastname", item.getLastname());
		data.put("sex", item.getSex());
		data.put("address", item.getAddress());
		data.put("alias", item.getAlias());
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		data.put("birthday", dt.format(item.getBirth()));
		data.put("deadday",(item.getDead()== null?"?":dt.format(item.getDead())));	
		response.getWriter().write(data.toString());
	}
}
