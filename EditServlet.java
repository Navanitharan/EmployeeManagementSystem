

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class EditServlet
 */

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out =response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emManagement", "root", "root");
			
			String id=request.getParameter("empid");
			String Name=request.getParameter("empname");
			String pass=request.getParameter("emppass");
			String mail=request.getParameter("empmail");
			String ctry=request.getParameter("empctry");
			
			
			PreparedStatement ps=con.prepareStatement("update emInfo set ID=?, Name=?, Password=?, Email=?, Country=? where id=?");
			ps.setString(1,id);
			ps.setString(2,Name);
			ps.setString(3,pass);
			ps.setString(4, mail);
			ps.setString(5, ctry);
			ps.setString(6, id);
			
			int count=ps.executeUpdate();
			
			out.println("<font color=green>"+count +"Row(s) Updated Sucessfully" +"</font></br>");
			out.println("<p><a href=EmployeeTable>click here to view the updated details</p>");
			ps.close();
			out.close();
			
		}catch(Exception e) {
			out.println(e);
		}
	}

}
