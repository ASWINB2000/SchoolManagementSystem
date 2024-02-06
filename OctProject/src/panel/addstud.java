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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addstud
 */
@WebServlet("/addstud")
public class addstud extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	public static PreparedStatement ps;
	public static Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addstud() {
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
		String n=request.getParameter("name");
		String reg=request.getParameter("reg_no");
		String p=request.getParameter("phone_number");
		String em=request.getParameter("email");
		String d=request.getParameter("department");
		String us=request.getParameter("username");
		String dob=request.getParameter("dob");
		String role="Student";
		String maxlgno="";
		try {
			HttpSession session=request.getSession(false);
			int q=(int)session.getAttribute("tid");
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			
			String sql="insert into login(username,password,role) values('"+us+"','"+dob+"','"+role+"')";
			PreparedStatement ps=con.prepareStatement(sql);//executing queries
			int i=ps.executeUpdate();
			if(i==1)
			{	String sql3="select max(lgno) from login";
			PreparedStatement ps3=con.prepareStatement(sql3);
				ResultSet rs=ps3.executeQuery();
				while(rs.next())
				{
					maxlgno=rs.getString("max(lgno)");
					String sql2="insert into student_table(name,email,username,dob,phone_number,department,reg_no,teacher_id,lgno)"
							+ "values('"+n+"','"+em+"','"+us+"','"+dob+"','"+p+"','"+d+"','"+reg+"','"+q+"','"+maxlgno+"')";
					String sql4="insert into marks(name) values('"+n+"')";
					PreparedStatement ps2=con.prepareStatement(sql2);
					PreparedStatement ps4=con.prepareStatement(sql4);
					int j=ps2.executeUpdate();
					int k=ps4.executeUpdate();
					if(j==1)
					{
						System.out.println("successfully added to student database");
						System.out.println("successfully added to  login database");
						response.sendRedirect("dashboard");
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
