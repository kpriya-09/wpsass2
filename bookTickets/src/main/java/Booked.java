

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Booked
 */
@WebServlet("/Booked")
public class Booked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booked() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession sess=request.getSession(false);
        String seats = (String) sess.getAttribute("seats");
        String city = (String) sess.getAttribute("city");
        String date = (String) sess.getAttribute("date");
        String Movie = (String) sess.getAttribute("movie");
        
        String sql = "SELECT * FROM shows";
try {
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		   date=sdf2.format(sdf.parse(date));
		} catch (Exception e) {
		    e.printStackTrace();
		}

		try {
		Class.forName("org.mariadb.jdbc.Driver");
		
	
		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/booking","root","");
		Statement s = con.createStatement();
		String htmlRespone = "<html> <body >";
        htmlRespone += "Movie Details at a date"+date+"<br/><table border=2><tr> <td>Country</td><td>City</td> <td>MovieName</td> <td>Date</td></tr> ";      
		ResultSet rs1=s.executeQuery(sql);
		
		while(rs1.next()){
			if(rs1.getString(4).equals(date)) {
				htmlRespone+="<tr/>"+"<td>"+rs1.getString(1)+"</td>"+"<td>"+rs1.getString(2)+"</td>"+"<td>"+
			rs1.getString(3)+"</td>"+"<td>"+rs1.getString(4)+"</td>"+"</tr>";
		}
		}
		htmlRespone+="</table></br>";
	
		htmlRespone += "Dates at while movie "+Movie+" is played <br/><table>"; 
     rs1=s.executeQuery(sql);
		while(rs1.next()) {
		
			if(rs1.getString(2).equals(city)) {
				
				htmlRespone+="<tr><td>"+rs1.getString(4)+"</td></tr>";}
			
		}
		
		htmlRespone+="</table></body></html>";
		out.println(htmlRespone);
		RequestDispatcher rd=request.getRequestDispatcher("/reports.html");
		rd.include(request, response);

		}


		 catch (Exception e) {
		// TODO Auto-generated catch block
			 e.printStackTrace();
		}
	}

}
