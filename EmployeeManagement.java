

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class EmployeeManagement
 */
@WebServlet("/EmployeeManagement")
public class EmployeeManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String Name,Password,Email,Country;
	String Id;
		
	
    /**
     * Default constructor. 
     */
    public EmployeeManagement() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//geting the response from html page
		PrintWriter out = response.getWriter();
		Id=request.getParameter("txtid");
		Name=request.getParameter("txtname");
		Password=request.getParameter("txtpwd");
		Email=request.getParameter("txtmail");
		Country=request.getParameter("txtctry");
		
		
		try {
			
			//updating the values to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emManagement", "root", "root");
			
			PreparedStatement ps =con.prepareStatement("insert into emInfo values(?,?,?,?,?)");
			
			ps.setString(1,Id);
			ps.setString(2, Name);
			ps.setString(3, Password);
			ps.setString(4, Email);
			ps.setString(5, Country);
			
			int count=ps.executeUpdate();
			
			out.println("<font color=green>"+count +"Row(s) Updated Sucessfully" +"</font></br>");
			out.println("<p><a href=EmployeeTable>click here to view the updated details</p>");
			ps.close();
			out.close();
			
		}catch (Exception e) {
			out.println("<font color=red>Record Faild</font>");
		}
	}	
}

