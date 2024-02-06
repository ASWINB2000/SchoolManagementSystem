package panel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class panel
 */
@WebServlet("/panel")
public class panel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public panel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n=request.getParameter("name");
		String emp=request.getParameter("EmployeeNumber");
		String p=request.getParameter("PhoneNumber");
		String em=request.getParameter("Email");
		String d=request.getParameter("Department");
		String u=request.getParameter("Username");
		String pa=request.getParameter("Password");
		String maxlgno="";
		String role="Teacher";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			String sql="insert into login(username,password,role) values('"+u+"','"+pa+"','"+role+"')";
			PreparedStatement ps=con.prepareStatement(sql);//executing queries
			
			int i=ps.executeUpdate();
		
			if(i==1)
			{	String sql3="select max(lgno) from login";
				PreparedStatement ps3=con.prepareStatement(sql3);
				ResultSet rs=ps3.executeQuery();
				while(rs.next())
				{
					 maxlgno=rs.getString("max(lgno)");
					 String sql2="insert into staffs_account(name,emp_no,phone_no,email,department,username,password,lgno)"
								+ "values('"+n+"','"+emp+"','"+p+"','"+em+"','"+d+"','"+u+"','"+pa+"','"+maxlgno+"')";
					 PreparedStatement ps2=con.prepareStatement(sql2);
					 int j=ps2.executeUpdate();
					 if(j==1)
					 {
						 response.sendRedirect("tlogin.html");
					 }
				}
				
				
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
