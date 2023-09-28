

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
 * Servlet implementation class EmployeeEdit
 */
@WebServlet("/EmployeeEdit")
public class EmployeeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String eid=request.getParameter("id");
		PrintWriter out =response.getWriter();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emManagement", "root", "root");
			ResultSet rs;
			PreparedStatement ps=con.prepareStatement("select * from emInfo where id=?");
			ps.setString(1, eid);
			rs=ps.executeQuery();
			
			while(rs.next()) 
			{
				
				out.print("<form action='EditServlet' method='Post'");
				out.print("<table>");
				
				out.print("<tr> <td> EmpId:</td> <td> <input type='text' name='empid' id='empid' value='" + rs.getString(1) + "'/> </td>  </tr>");
				out.print("<tr> <td> Name:</td> <td> <input type='text' name='empname' id='empname' value='" + rs.getString(2) + "'/> </td>  </tr>");
				out.print("<tr> <td> Password:</td> <td> <input type='text' name='emppass' id='emppass' value='" + rs.getString(3) + "'/> </td>  </tr>");
				out.print("<tr> <td> Mail</td> <td> <input type='text' name='empmail' id='empmail' value='" + rs.getString(4) + "'/> </td>  </tr>");
				out.print("<tr> <td> Country</td> <td> <input type='text' name='empctry' id='empctry' value='" + rs.getString(5) + "'/> </td>  </tr>");
				out.print("<td colspan='2'> <input type='submit' name='edit' id='edit' value='Edit'/> </td>  </tr>");
				
				out.print("</table>");
				out.print("</form>");
			}
			
			ps.close();
			out.close();
		}catch(Exception e) {
			out.println(e);
		}
	}

}
