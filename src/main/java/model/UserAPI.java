package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	User userObj = new User();
	
    public UserAPI() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = userObj.insertUser(
				request.getParameter("userCode"), 	
				request.getParameter("userName"),
				request.getParameter("nic"),
				request.getParameter("phone"), 
				request.getParameter("email"), 
				request.getParameter("address"),
				request.getParameter("password"));
		
		response.getWriter().write(output);
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			Map paras = getParasMap(request); 
			
			String output = userObj.updateUser(
					paras.get("hidUserIDSave").toString(), 
					paras.get("userCode").toString(), 
					paras.get("userName").toString(),
					paras.get("nic").toString(),
					paras.get("phone").toString(), 
					paras.get("email").toString(), 
					paras.get("address").toString(),
					paras.get("password").toString()); 
				    
			response.getWriter().write(output); 

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		String output = userObj.deleteUser(paras.get("userID").toString()); 
		response.getWriter().write(output); 

	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		
		try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 
			 String[] params = queryString.split("&");
			 
			 for (String param : params) 
			 { 
			 
				 String[] p = param.split("="); 
				 map.put(p[0], p[1]); 
			 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

}
