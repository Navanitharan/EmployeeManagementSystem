import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/EmployeeTable")
public class EmployeeTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emManagement", "root", "root");
			Statement stmt = con.createStatement();
			
			ResultSet rs= stmt.executeQuery("Select * from emInfo");
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html><body>");
			out.println("<h3>Employee Details</h3>");
			out.println("<table border=1> <tr><th>ID</th><th>Name</th><th>Password</th><th>Email-ID</th><th>Country</th><th>Edit</th><th>Delete</th></tr>.");
			while(rs.next()) {
				out.println("<tr><td>"+rs.getString(1) +"</td><td>"+ rs.getString(2)+"</td><td>"+rs.getString(3) +"</td><td>"+ rs.getString(4) +"</td><td>"+ rs.getString(5)+"</td>");
				
				out.println("<td>"+ "<a href='EmployeeEdit?id=" +rs.getString(1) +"'>Edit </a>"+"</td>");
				out.println("<td>"  + "<a href='EmployeeDelete?id=" +  rs.getString(1)  + "'> Delete </a>" + "</td>"+"</tr>");
			}
			out.println("</table></body></html>");
		}catch (Exception e) {
			System.out.println(e);
	}
	}
}