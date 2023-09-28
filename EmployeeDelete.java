

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

/**
 * Servlet implementation class EmployeeDelete
 */
public class EmployeeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emManagement", "root", "root");
			
			String id=request.getParameter("id");
			
			
			
			PreparedStatement ps=con.prepareStatement("delete from emInfo where id=?");
			ps.setString(1,id);
			
			
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
