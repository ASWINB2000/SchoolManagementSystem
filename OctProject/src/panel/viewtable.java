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

/**
 * Servlet implementation class viewtable
 */
@WebServlet("/viewtable")
public class viewtable extends HttpServlet {
	public static Connection con;
	public static Statement ps;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewtable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		HttpSession session=request.getSession(false);
		int z=(int)session.getAttribute("tid");
		Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
		con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
		String sql="select * from student_table where teacher_id='"+z+"'";
		ps=con.createStatement();
		ResultSet rs=ps.executeQuery(sql);
		PrintWriter p=response.getWriter();
		p.print("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>Viewing Student Details</title>\r\n" + 
				"    <link rel=\"stylesheet\" href=\"viewtable.css\">\r\n" + 
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
				"    <div class=\"glass\">\r\n" + 
				"        <div class=\"top\">\r\n" + 
				"            <div class=\"table-container\">\r\n" + 
				"            <table>\r\n" + 
				"                <tr>\r\n" + 
				"                    <th>NAME</th>\r\n" + 
				"                    <th>EMAIL</th>\r\n" + 
				"                    <th>DEPARTMENT</th>\r\n" + 
				"                    <th>REG-NO</th>\r\n" + 
				"                    <th>PHONE-NO</th>\r\n" + 
				"                    <th>action</th>\r\n" + 
				"                </tr>");
				while(rs.next())
				{
//					p.print("<tr>\r\n" + 
//							"                <td>'"+rs.getString("name")+"'</td>\r\n" + 
//							"                <td>'"+rs.getString("email")+"'</td>\r\n" + 
//							"                <td>'"+rs.getString("department")+"'</td>\r\n" + 
//							"                <td>'"+rs.getString("reg_no")+"'</td>\r\n" + 
//							"                <td>'"+rs.getString("phone_number")+"'</td>\r\n" + 
//							p.print("<td><a href="">view</a> <a href="">delete</a></td>") +
//							"            </tr>");
					
					
				    p.print("<tr>");
	                p.print("<td>"+rs.getString("name")+"</td>");
	                p.print("<td>"+rs.getString("email")+"</td>");
	                p.print("<td>"+rs.getString("department")+"</td>");
	                p.print("<td>"+rs.getString("reg_no")+"</td>");
	                p.print("<td>"+rs.getString("phone_number")+"</td>");
	                p.print("<td><a class='viewdelete' href='viewstudentdetails?sid="+rs.getInt("slno")+"'>VIEW</a><a class='viewdelete' onclick=\" return checkdelete()\"href='deletestudentdetails?sid="+rs.getInt("slno")+"'>DELETE</a><a class='viewdelete' href='viewmarksteacher?sid="+rs.getInt("slno")+"'>MARK</a></td></tr>");
	                
				}
				p.print("</table>\r\n" + 
						"             </div>\r\n" + 
						"         </div>\r\n" + 
						"    </div>\r\n" + 
						"    <script>\r\n" + 
						"        function checkdelete(){\r\n" + 
						"            return confirm('Are you sure you want to delete this Student Record ?');\r\n" + 
						"        }\r\n" + 
						"    </script>\r\n" + 
						"</body>\r\n" + 
						"</html>");
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
