package com.example.appengine.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.appengine.dao.GenealogyDAO;
import com.example.appengine.dao.PeopleDAO;
import com.example.appengine.model.Genealogy;
import com.example.appengine.model.People;
import com.example.appengine.model.User;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ViewGenealogyServlet
 */
@WebServlet("/ViewGenealogyServlet")
public class ViewGenealogyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewGenealogyServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
		String command = request.getParameter("command");
		switch (command) {
		case "get_list":
			getListGenealogy(request, response);
			break;
		case "get_list_by_id":
			getListPeopleByGenealogyId(request, response);
			break;
		case "get_data_view_grid":
			getDataViewGridByIdGenealogy(request, response);
			break;
		case "get_item_view_edit":
			getItemGenealogyJson(request, response);
			break;
		default:
			break;
		}

	}

	private void getItemGenealogyJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GenealogyDAO _DAO = new GenealogyDAO();
		String id_genealogy = request.getParameter("id_genealogy");
		Genealogy item = _DAO.getItem(Long.parseLong(id_genealogy));
		JSONObject data = new JSONObject();
		data.put("id", item.getId());
		data.put("name", item.getName());
		data.put("description", item.getDescription());	
		response.getWriter().write(data.toString());
	}

	private void getListPeopleByGenealogyId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PeopleDAO _DAO = new PeopleDAO();
		String id_genealogy = request.getParameter("id_genealogy");
		ArrayList<People> Peoples = _DAO.getListPeopleByGenealodyID(Long.parseLong(id_genealogy));
		JSONObject data = new JSONObject();
		for (People item : Peoples) {
			if (item.getSex() == 1) {
				JSONObject jObj = new JSONObject();
				jObj.put("id", item.getId());
				jObj.put("firstname", item.getFirstname());
				jObj.put("lastname", item.getLastname());
				data.put(item.getId(), jObj);
			}

		}
		response.getWriter().write(data.toString());

	}

	private void getListGenealogy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GenealogyDAO _DAO = new GenealogyDAO();
		User user = (User) request.getSession().getAttribute(LoginServlet.USER_SESSION);
		ArrayList<Genealogy> genealogys = _DAO.getList(user.getId());
		JSONObject data = new JSONObject();
		for (Genealogy item : genealogys) {
			JSONObject jObj = new JSONObject();
			jObj.put("id", item.getId());
			jObj.put("name", item.getName());
			data.put(item.getId(), jObj);
		}
		response.getWriter().write(data.toString());
	}

	private ArrayList<People> getChildFromIdFather(long id_father) {
		ArrayList<People> list = new PeopleDAO().getListChildByIdFather(id_father);
		return list;
	}

	private ArrayList<People> getHusbandWifeFromIdFather(long id_husband) {

		People husband = new PeopleDAO().getItem(id_husband);
		ArrayList<People> list = new PeopleDAO().getListWifeByIdHusband(id_husband);
		list.add(0, husband);
		//list.add(husband);
		return list;
	}

	private JSONObject getJSonFromList(ArrayList<People> list) {
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/mm/yyyy");
		JSONObject dataJSon = new JSONObject();
		if (list == null) {
			return null;
		}
		for( People item:list){
			JSONObject jObj = new JSONObject();
			jObj.put("id", item.getId());
			jObj.put("firstname", item.getFirstname());
			jObj.put("lastname", item.getLastname());
			jObj.put("birthday", dt1.format(item.getBirth()));
			jObj.put("deadday", item.getDead() == null ? "?" : dt1.format(item.getDead()));
			jObj.put("sex", item.getSex());
			jObj.put("address", item.getAddress());
			
			dataJSon.put(item.getId(), jObj);
		}
		JSONObject dataResult = new JSONObject();
		dataResult.put("item", dataJSon);
		
		// System.out.println(list.size() + "|" + dataResult.toString());
		return dataResult;
	}

	private JSONObject getJSonFromItem(People item) {
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/mm/yyyy");
		JSONObject dataJSon = new JSONObject();
		if (item == null) {
			return null;
		}
		JSONObject jObj = new JSONObject();
		jObj.put("id", item.getId());
		jObj.put("firstname", item.getFirstname());
		jObj.put("lastname", item.getLastname());
		jObj.put("birthday", dt1.format(item.getBirth()));
		jObj.put("deadday", item.getDead() == null ? "?" : dt1.format(item.getDead()));
		jObj.put("sex", item.getSex());

		dataJSon.put(item.getId(), jObj);
		JSONObject dataResult = new JSONObject();
		dataResult.put("item", dataJSon);
		return dataResult;
	}

	private JSONObject getJSONDataFamily(int numOfDeep, long id_husband) {
		JSONObject dataJSon = new JSONObject();
		if (numOfDeep <= 0) {
			return null;
		}
		int deep = numOfDeep - 1;
		// get tất cả vợ của các da
		ArrayList<People> fathers = getHusbandWifeFromIdFather(id_husband);
		if (fathers == null)
			return null;

		dataJSon.put("cha", getJSonFromList(fathers));
		
		ArrayList<People> childs = getChildFromIdFather(id_husband);
		if (childs == null) {
			return null;
		} else {
			JSONObject dataChild = new JSONObject();
			for (int i = 0; i < childs.size(); i++) {
				if (childs.get(i).getSex() == 1) {
					dataChild.put(i, getJSONDataFamily(deep, childs.get(i).getId()));
				} else {
					if(deep>=1){
						JSONObject endJson = new JSONObject();
						endJson.put("cha", getJSonFromItem(childs.get(i)));
						dataChild.put(i, endJson);
					}
					
				}
			}
			dataJSon.put("con", dataChild);
		}
		return dataJSon;
	}

	private void getDataViewGridByIdGenealogy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_genealogy = "", id_people = "", deep = "";
		id_genealogy = request.getParameter("id_genealogy");
		id_people = request.getParameter("id_people");

		deep = request.getParameter("deep");
		JSONObject dataViewGrid = new JSONObject();
		// get Genealogy
		Genealogy genealogy;
		if(id_genealogy.equals("-1")){
			User user = (User)request.getSession().getAttribute(LoginServlet.USER_SESSION);
			genealogy = new GenealogyDAO().getLastItem(user.getId());
		}else{
			genealogy = new GenealogyDAO().getItem(Long.parseLong(id_genealogy));
		}
		if (genealogy == null) {
			return;
		}
		JSONObject dataGenealogy = new JSONObject();
		dataGenealogy.put("id", genealogy.getId());
		dataGenealogy.put("name", genealogy.getName());
		dataGenealogy.put("description", genealogy.getDescription());
		dataViewGrid.put("genealogy", dataGenealogy);
		// get people list
		if(id_people.equals("-1")){
			try {
				People root = new PeopleDAO().getRootPeopleByGenealogy(genealogy.getId());
				JSONObject datapeople = getJSONDataFamily(Integer.parseInt(deep), root.getId());
				dataViewGrid.put("people", datapeople);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}else{
			JSONObject datapeople = getJSONDataFamily(Integer.parseInt(deep), Long.parseLong(id_people));
			dataViewGrid.put("people", datapeople);
		}
		response.getWriter().write(dataViewGrid.toString());
	}

	public static void main(String[] args) {
		// String id_genealogy = "1495356731138", id_people = "0", deep = "3";
		// JSONObject dataViewGrid = new JSONObject();
		// //get Gênalogy
		// Genealogy genealogy = new
		// GenealogyDAO().getItem(Long.parseLong(id_genealogy));
		// JSONObject dataGenealogy = new JSONObject();
		// dataGenealogy.put("id", genealogy.getId());
		// dataGenealogy.put("name", genealogy.getName());
		// dataGenealogy.put("description", genealogy.getDescription());
		// dataViewGrid.put("genealogy", dataGenealogy);
		// // get people list
		// JSONObject datapeople = new
		// ViewGenealogyServlet().getJSONDataFamily(Integer.parseInt(deep),Long.parseLong(id_people));
		// dataViewGrid.put("people", datapeople);
		// System.out.println(dataViewGrid.toString());

		for (People item : new ViewGenealogyServlet().getHusbandWifeFromIdFather(0)) {
			System.out.println(item.toString());
		}
		// System.out.println(new PeopleDAO().getItem(0));
	}
}
