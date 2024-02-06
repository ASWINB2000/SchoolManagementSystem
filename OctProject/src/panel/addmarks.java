package panel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addmarks
 */
@WebServlet("/addmarks")
public class addmarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection con;
	public static PreparedStatement ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addmarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phy=request.getParameter("physics");
		String chem=request.getParameter("chemistry");
		String math=request.getParameter("math");
	
		int phyy=Integer.parseInt(phy);//converting into integer for result valuation
		int chemm=Integer.parseInt(chem);//converting into integer for result valuation
		int mathh=Integer.parseInt(math);//converting into integer for result valuation
		String resultphysics="";
		String resultchemistry="";
		String resultmath="";
		if(phyy>40)
			resultphysics="PASS";
		else
			resultphysics="FAIL";
		if(chemm>40)
			resultchemistry="PASS";
		else
			resultchemistry="FAIL";
		if(mathh>40)
			resultmath="PASS";
		else
			resultmath="FAIL";
		
		String id=request.getParameter("uid");
	    LocalDateTime now = LocalDateTime.now();  
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String datetime = now.format(format);   
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //registering the driver class 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/loginpage","root","root");//creating connection
			String sql="update marks set physics='"+phy+"',chemistry='"+chem+"',math='"+math+"',dt='"+datetime+"',resultphy='"+resultphysics+"',resultchem='"+resultchemistry+"',resultmath='"+resultmath+"' where slno='"+id+"'";
			ps=con.prepareStatement(sql);
			int i=ps.executeUpdate();
			if(i==1)
			{
				System.out.println("added to marks");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
