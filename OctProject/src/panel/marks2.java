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
 * Servlet implementation class marks2
 */
@WebServlet("/marks2")
public class marks2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
	public static Statement ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public marks2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter p=response.getWriter();
		String q=request.getParameter("sid");
		String sql="select name from student_table where slno='"+q+"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			while(rs.next())
			{
				p.print("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>ADD MARKS!</title>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"marks.css\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
						"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
						"    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@500&family=Young+Serif&display=swap\" rel=\"stylesheet\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\">\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"    <div class=\"glass\">\r\n" + 
						"    <div class=\"heading\"> <h2>STUDENT NAME: "+rs.getString("name").toUpperCase()+"</h2></div>\r\n" + 
						"    \r\n" + 
						"        <div class=\"box\">\r\n" + 
						"         <form action='addmarks'>\r\n" + 
						"            <div class=\"mb-3\">\r\n" + 
						"              <label for=\"exampleInputEmail1\" class=\"form-label\">Enter marks for Physics.</label>\r\n" + 
						"              <input type=\"text\" class=\"form-control\" placeholder=\"/100\" name='physics'>\r\n" + 
						"             \r\n" + 
						"            </div>\r\n" + 
						"            <div class=\"mb-3\">\r\n" + 
						"              <label for=\"exampleInputPassword1\" class=\"form-label\">Enter Marks for Chemistry.</label>\r\n" + 
						"              <input type=\"text\" class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"/100\" name='chemistry'>\r\n" + 
						"            </div>\r\n" + 
						"            <div class=\"mb-3\">\r\n" + 
						"                <label for=\"exampleInputEmail1\" class=\"form-label\">Enter marks for Math.</label>\r\n" + 
						"                <input type=\"text\" class=\"form-control\" placeholder=\"/100\" name='math'>\r\n" +
						"				 <input type=\"hidden\" name=\"uid\" value='"+q+"'>" +
						"               \r\n" + 
						"              </div>\r\n" + 
						"            <button type=\"submit\" class=\"btn btn-primary\">Submit Mark</button>\r\n" + 
						"            <button type=\"button\" class=\"btn btn-outline-light lightbutton\" >Back</button>\r\n" + 
						"          </form>\r\n" + 
						"    </div>\r\n" + 
						"</div>\r\n" + 
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
