

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
 * Servlet implementation class GenerateReports
 */
@WebServlet("/GenerateReports")
public class GenerateReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateReports() {
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
		PrintWriter out=response.getWriter();
		String sql = "SELECT city,ticketsbooked,sum(ticketprice*ticketsbooked) as total FROM shows GROUP BY CITY" ;
		String sql1 = "SELECT MOVIE,sum(ticketprice*ticketsbooked) as total FROM shows GROUP BY MOVIE" ;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/booking","root","");
			Statement s = con.createStatement();
			String htmlRespone = "<html>";
	        htmlRespone += "TotalRevenue and TicketsBooked in a City<br/><table border=2><tr><td>City</td> <td>Tickets Booked</td> <td>Total Revenue</td></tr> ";      
			ResultSet rs1=s.executeQuery(sql);
			while(rs1.next()){
					htmlRespone+="<tr/>"+"<td>"+rs1.getString(1)+"</td>"+"<td>"+rs1.getString(2)+"<td>"+rs1.getString(3)+"</td></tr>";
			}
			htmlRespone+="</table></html>";
			htmlRespone += "<br/> Maximum Revenue for a Movie<br/>"
					+ "<table border=2><tr><td>Movie</td> <td>Maximum Revenue</td></tr> ";
			ResultSet rs2=s.executeQuery(sql1);
			while(rs2.next()){
					htmlRespone+="<tr/>"+"<td>"+rs2.getString(1)+"</td>"+"<td>"+rs2.getString(2)+"</td>"+"</tr>";
			}
			htmlRespone+="</table></html>";
			out.println(htmlRespone);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

	}

}
