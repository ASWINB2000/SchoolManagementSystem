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
 * Servlet implementation class viewstudentdetails
 */
@WebServlet("/viewstudentdetails")
public class viewstudentdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
	public static Statement ps;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewstudentdetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p=response.getWriter();
		String sid=request.getParameter("sid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			String sql="select * from student_table where slno='"+sid+"'";
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next())
			{
				p.print("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>Viewing Student Details</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"viewstudentdetails.css\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@500&family=Young+Serif&display=swap\" rel=\"stylesheet\">\r\n" + 
						"    <style>\r\n" + 
						"        table {\r\n" + 
						"            border-collapse: collapse;\r\n" + 
						"            width: auto;\r\n" + 
						"            margin: auto;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        th, td {\r\n" + 
						"            border: 2px solid black;\r\n" + 
						"            padding: 20px;\r\n" + 
						"            text-align: center;\r\n" + 
						"            width: 30px;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        th {\r\n" + 
						"            background-color: gainsboro;\r\n" + 
						"        }\r\n" + 
						"      \r\n" + 
						"        .centered{\r\n" + 
						"            color: rgb(34, 32, 36);\r\n" + 
						"            text-align: center;\r\n" + 
						"            \r\n" + 
						"        }\r\n" + 
						"       \r\n" + 
						"       \r\n" + 
						"\r\n" + 
						"    </style>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"    <nav>\r\n" + 
						"        <ul>\r\n" + 
						"            <li>\r\n" + 
						"                <a href=\"#\" class=\"logo\"><img src=\"gen5.jpeg\" alt=\"\">\r\n" + 
						"                <span class=\"nav-item\"> Teacher's Panel</span>\r\n" + 
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
						"            <li><a href=\"addstudent.html\">\r\n" + 
						"                <i class=\"fas fa-user-graduate\"></i>\r\n" + 
						"                <span class=\"nav-item\">Add Student</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            <li><a href=\"#\">\r\n" + 
						"                <i class=\"fas fa-user-slash\"></i>\r\n" + 
						"                <span class=\"nav-item\">Remove Student</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            <li><a href=\"#\">\r\n" + 
						"                <i class=\"fas fa-table\"></i>\r\n" + 
						"                <span class=\"nav-item\">View Student Table</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            <li><a href=\"#\" class=\"logout\">\r\n" + 
						"                <i class=\"fas fa-sign-out-alt\"></i>\r\n" + 
						"                <span class=\"nav-item\">Log out</span>\r\n" + 
						"            </a></li>\r\n" + 
						"            \r\n" + 
						"        </ul>\r\n" + 
						"    </nav>\r\n" + 
						"    <div class=\"glass\">");
				p.print("<div class=\"content\">\r\n" + 
						"            <h2>SLNO: <span class=\"centered\">"+rs.getString("slno")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>NAME: <span class=\"centered\">"+rs.getString("name")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>EMAIL: <span class=\"centered\">"+rs.getString("email")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>USERNAME: <span class=\"centered\">"+rs.getString("username")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>DATE OF BIRTH: <span class=\"centered\">"+rs.getString("dob")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>PHONE-NO: <span class=\"centered\">"+rs.getString("phone_number")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>DEPARTMENT: <span class=\"centered\">"+rs.getString("department")+"</span></h2>\r\n" + 
						"            <br>\r\n" + 
						"            <h2>REG-NO: <span class=\"centered\">"+rs.getString("reg_no")+"</span></h2>\r\n" + 
						"        </div>");
				p.print("</div>\r\n" + 
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
