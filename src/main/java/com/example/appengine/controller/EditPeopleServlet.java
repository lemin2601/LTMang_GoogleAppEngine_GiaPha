package com.example.appengine.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.appengine.dao.PeopleDAO;
import com.example.appengine.model.People;

/**
 * Servlet implementation class EditPeopleServlet
 */
@WebServlet("/EditPeopleServlet")
public class EditPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public EditPeopleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if(request.getSession().getAttribute(LoginServlet.USER_SESSION)==null) return;
		request.setCharacterEncoding("UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
//			PrintWriter writer = response.getWriter();
//			writer.println("Error: Form must has enctype=multipart/form-data.");
//			writer.flush();
//			return;
			//upload bÃ¬nh thÆ°á»�ng
			String id = "", firstname = "", lastname = "", sex = "", birthday = "", deadday = "", alias = "",
					address = "";
			id = request.getParameter("id");
			firstname = request.getParameter("firstname");
			lastname = request.getParameter("lastname");
			sex = request.getParameter("sex");
			birthday = request.getParameter("birthday");
			deadday = request.getParameter("deadday");
			alias = request.getParameter("alias");
			address = request.getParameter("address");
			// tiáº¿n hÃ nh add database
			SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
			People item = new People();
			item.setId(Long.parseLong(id));
			item.setFirstname(firstname);
			item.setLastname(lastname);
			item.setSex(Integer.parseInt(sex));
			item.setAddress(address);
			item.setAlias(alias);
			try {
				item.setBirth(dt.parse(birthday));
				item.setDead(dt.parse(deadday));
			} catch (Exception e) {
			}
			PeopleDAO _DAO = new PeopleDAO();
			int result = 0;
			result = _DAO.editItem(item);
			if(result > 0){
				response.getWriter().println("success");
			}else{
				response.getWriter().println("failed");
			}
			
		} else {
			// configures upload settings
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// sets memory threshold - beyond which files are stored in disk
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			// sets temporary location to store files
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			// sets maximum size of upload file
			upload.setFileSizeMax(MAX_FILE_SIZE);
			// sets maximum size of request (include file + form data)
			upload.setSizeMax(MAX_REQUEST_SIZE);
			try {
				// parses the request's content to extract file data
				@SuppressWarnings("unchecked")
				List<FileItem> formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					// iterates over form's fields
					String id = "", firstname = "", lastname = "", sex = "", birthday = "", deadday = "", alias = "",
							address = "";
					InputStream img = null;
					for (FileItem item : formItems) {
						if (item.isFormField()) {
							switch (item.getFieldName()) {
							case "id":
								id = item.getString();
								id = new String(id.getBytes("iso-8859-1"), "UTF-8");
								break;

							case "firstname":
								firstname = item.getString();
								firstname = new String(firstname.getBytes("iso-8859-1"), "UTF-8");
								break;
							case "lastname":
								lastname = item.getString();
								lastname = new String(lastname.getBytes("iso-8859-1"), "UTF-8");
								break;
							case "sex":
								sex = item.getString();
								sex = new String(sex.getBytes("iso-8859-1"), "UTF-8");
								break;
							case "birthday":
								birthday = item.getString();
								birthday = new String(birthday.getBytes("iso-8859-1"), "UTF-8");
								break;
							case "deadday":
								deadday = item.getString();
								deadday = new String(deadday.getBytes("iso-8859-1"), "UTF-8");
								break;
							case "alias":
								alias = item.getString();
								alias = new String(alias.getBytes("iso-8859-1"), "UTF-8");
								break;
							case "address":
								address = item.getString();
								address = new String(address.getBytes("iso-8859-1"), "UTF-8");
								break;
							default:
								break;
							}
							String fieldName = item.getFieldName();
							String value = item.getString();
							System.out.println(fieldName + "|" + value);
						} else if (!item.isFormField()) {
							img = item.getInputStream();
							if (img == null) {
								response.getWriter().println("Lhong cÃ³ áº£nh trong nÃ y");
							}
						}
					}
					// tiáº¿n hÃ nh add database
					SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
					People item = new People();
					item.setAddress(address);
					item.setAlias(alias);
					try {
						item.setBirth(dt.parse(birthday));
						item.setDead(dt.parse(deadday));
					} catch (Exception e) {
					}
					item.setFirstname(firstname);
					item.setId(Long.parseLong(id));
					item.setImg(img);
					item.setLastname(lastname);
					item.setSex(Integer.parseInt(sex));
					response.getWriter().println(item.toString());
					PeopleDAO _DAO = new PeopleDAO();
					if(item.getSex() == 0){
						_DAO.deleteAllBranch(item.getId());
					}
					int result = 0;
					result = _DAO.updateItem(item);
					if(result > 0){
						response.getWriter().println("success");
					}else{
						response.getWriter().println("failed");
					}
				}
			} catch (Exception ex) {
				response.getWriter().println("There was an error: " + ex.getMessage());
			}
		}

	}

}
