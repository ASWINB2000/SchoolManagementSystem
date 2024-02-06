package panel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	public static Connection con;
	public static Statement ps;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		String usern=request.getParameter("Username");
		String pass=request.getParameter("Password");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			String sql="select * from login where username='"+usern+"' and password='"+pass+"'";
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next()) 
			{
				
				int loginno=0;
				String role=rs.getString("role");
				HttpSession session=request.getSession();
				switch(role)
				{
				case "Teacher":
				{
//					session.setAttribute("tid",rs.getInt("slno") );
					session.setAttribute("loginno", rs.getInt("lgno"));
					response.sendRedirect("dashboard");
					break;
				}
				case "Student":
				{
//					session.setAttribute("tid",rs.getInt("slno") );
					session.setAttribute("loginno", rs.getInt("lgno"));
					response.sendRedirect("dashboardstudent");
					break;
				}
				}
				
//				 HttpSession session=request.getSession(); old code
//				 session.setAttribute("tid",rs.getInt("slno") );
				
				
				
			 }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}

}
