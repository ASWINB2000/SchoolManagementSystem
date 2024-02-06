package panel;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class deletestudentdetails
 */
@WebServlet("/deletestudentdetails")
public class deletestudentdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
	public static PreparedStatement ps;
	public static Statement p;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletestudentdetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String sid=request.getParameter("sid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			String sql3="select lgno from student_table where slno="+sid+"";
			String loginno="";
			p=con.createStatement();
			ResultSet rs=p.executeQuery(sql3);
			while(rs.next())
			{
				loginno=rs.getString("lgno");
			}
			String sql="delete from student_table where slno="+sid+"";
			String sql2="delete from marks where slno="+sid+"";
			String sql4="delete from login where lgno="+loginno+"";
			ps=con.prepareStatement(sql);
			int i=ps.executeUpdate();
			ps=con.prepareStatement(sql2);
			int j=ps.executeUpdate();
			ps=con.prepareStatement(sql4);
			int k=ps.executeUpdate();
			System.out.println("hi2");
			if(i==1 && j==1 && k==1)
			{
				response.sendRedirect("viewtable");
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
