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

/**
 * Servlet implementation class viewmarksstudent
 */
@WebServlet("/viewmarksstudent")
public class viewmarksstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
	public static Statement ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewmarksstudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String n=request.getParameter("sid");
			String mentor="";
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			String sql="select * from student_table where slno='"+n+"'";
			ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sql);
			PrintWriter p=response.getWriter();
			p.print("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <title>VIEW MARKS</title>\r\n" + 
					"    <link rel=\"stylesheet\" href=\"viewmarksteacher.css\">\r\n" + 
					"    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + 
					"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + 
					"    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@500&family=Young+Serif&display=swap\" rel=\"stylesheet\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\">\r\n" + 
					"\r\n" + 
					"</head>");
			
		
			while(rs.next())
			{
				mentor=rs.getString("teacher_id");
				
				p.print("<body>\r\n" + 
						"    <div class=\"glass\">\r\n" + 
						"        <div>\r\n" + 
						"            <h1 class=\"heading-m\">MARK LIST</h1>\r\n" + 
						"            <h2 class=\"heading\">NAME: "+rs.getString("name").toUpperCase()+"</h2>\r\n" + 
						"            <h2 class=\"heading\">REG-NO: "+rs.getString("reg_no")+"</h2>\r\n");
				
			}
			String sql2="select name from staffs_account where slno='"+mentor+"'";
			ResultSet rs2=ps.executeQuery(sql2);
			while(rs2.next())
			{
				p.print(" <h2 class=\"heading\">TEACHER: PROFESSOR "+rs2.getString("name").toUpperCase()+"</h2>\r\n" + 
						"        </div> ");
			}
			
			p.print(" <div class=\"tb\">\r\n" + 
					"        <table  class=\"table table-hover t \">\r\n" + 
					"            <thead>\r\n" + 
					"              <tr class=\"table-dark\">\r\n" + 
					"                <th scope=\"col\">#</th>\r\n" + 
					"                <th scope=\"col\">SUBJECTS</th>\r\n" + 
					"                <th scope=\"col\">TOTAL MARKS</th>\r\n" + 
					"                <th scope=\"col\">MARKS OBTAINED</th>\r\n" + 
					"                <th scope=\"col\">RESULT</th>\r\n" + 
					"                <th scope=\"col\">RESULT PUBLISHED</th>\r\n" + 
					"              </tr>\r\n" + 
					"            </thead>");
			String sql3="select * from marks where slno='"+n+"'";
			ResultSet rs3=ps.executeQuery(sql3);
			while(rs3.next())
			{
			p.print(" <tbody>\r\n" + 
					"              <tr class=\"table-info\">\r\n" + 
					"                <th scope=\"row\">1</th>\r\n" + 
					"                <td >Physics</td>\r\n" + 
					"                <td>100</td>\r\n" + 
					"                <td>"+rs3.getString("physics")+"</td>\r\n" + 
					"                <td>"+rs3.getString("resultphy")+"</td>\r\n" + 
					"                <td>"+rs3.getString("dt")+"</td>\r\n" + 
					"                \r\n" + 
					"              </tr>\r\n" + 
					"              <tr class=\"table-info\">\r\n" + 
					"                <th scope=\"row\">2</th>\r\n" + 
					"                <td>Chemistry</td>\r\n" + 
					"                <td>100</td>\r\n" + 
					"                <td>"+rs3.getString("chemistry")+"</td>\r\n" + 
					"                <td>"+rs3.getString("resultchem")+"</td>\r\n" + 
					"                <td>"+rs3.getString("dt")+"</td>\r\n" + 
					"              </tr>\r\n" + 
					"              <tr class=\"table-info\">\r\n" + 
					"                <th scope=\"row\">3</th>\r\n" + 
					"                <td>Math</td>\r\n" + 
					"                <td>100</td>\r\n" + 
					"                <td>"+rs3.getString("math")+"</td>\r\n" + 
					"                <td>"+rs3.getString("resultmath")+"</td>\r\n" + 
					"                <td>"+rs3.getString("dt")+"</td>\r\n" + 
					"              </tr>\r\n" + 
					"            </tbody>\r\n" + 
					"          </table>\r\n" + 
					"           <div>\r\n" + 
					"             <a href='dashboardstudent' class=\"btn btn-outline-info backbtn\">Back</a>\r\n" + 
					"            </div>");
			p.print(" </div>\r\n" + 
					"  </div>\r\n" + 
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
