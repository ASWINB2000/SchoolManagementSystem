package panel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;

/**
 * Servlet implementation class dashboardstudent
 */
@WebServlet("/dashboardstudent")
public class dashboardstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
	public static Statement ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashboardstudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			HttpSession session=request.getSession(false);old code
//			int v=(int)session.getAttribute("tid");
//			session.setAttribute("tids",v );
			HttpSession session=request.getSession(false);
		
//			int v=(int)session.getAttribute("tid");
//			System.out.println("gfdgfsgfdg"+v);
//			session.setAttribute("tids",v );
			
			int loginno=(int)session.getAttribute("loginno");
			
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
		
			String sql="select * from student_table where lgno="+loginno+"";
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			PrintWriter p=response.getWriter();
			while(rs.next())
			{
				p.print("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>Student-DashBoard</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"dashboards.css\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@500&family=Young+Serif&display=swap\" rel=\"stylesheet\">\r\n" + 
						"\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"    <nav>\r\n" + 
						"        <ul>\r\n" + 
						"            <li>\r\n" + 
						"                <a href=\"#\" class=\"logo\"><img src=\"gen5.jpeg\" alt=\"\">\r\n" + 
						"                <span class=\"nav-item\"> Student Panel</span>\r\n" + 
						"            </a>\r\n" + 
						"            </li>\r\n" + 
						"            <li><a href=\"#\">\r\n" + 
						"                <i class=\"fas fa-home\"></i>\r\n" + 
						"                <span class=\"nav-item\">Home</span>\r\n" + 
						"            </a>\r\n" + 
						"        </li>\r\n" + 
						"            <li><a href=\"#\">\r\n" + 
						"                <i class=\"fas fa-user\"></i>\r\n" + 
						"                <span class=\"nav-item\">Profile</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            <li><a href=\"viewmarksstudent?sid="+rs.getString("slno")+"\">\r\n" + 
						"                <i class=\"fas fa-poll\"></i>\r\n" + 
						"                <span class=\"nav-item\">View Result</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            <li><a href=\"#\">\r\n" + 
						"            <li><a href=\"#\" class=\"logout\">\r\n" + 
						"                <i class=\"fas fa-sign-out-alt\"></i>\r\n" + 
						"                <span class=\"nav-item\">Log out</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            \r\n" + 
						"        </ul>\r\n" + 
						"    </nav>\r\n" + 
						"    <div class=\"top\">\r\n" + 
						"        <h4>WELCOME   "+rs.getString("name").toUpperCase()+" </h4>\r\n" + 
						"    </div>\r\n" + 
						"</body>\r\n" + 
						"</html>");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
