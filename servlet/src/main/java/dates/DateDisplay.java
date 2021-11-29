package dates;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class DateDisplay
 */
@WebServlet("/DateDisplay")
public class DateDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession(true);
	     //Get writer.
	     response.setContentType("text/html");
	     PrintWriter pw = response.getWriter(); pw.print("<B>");
	     
	     //Display date/time of last access.
	     Date date = (Date)hs.getAttribute("date"); if(date != null) {
	    	 pw.print("Last access: " + date + "<br>");
	     }
		// Display current date/time.
	
		date = new Date(); hs.setAttribute("date", date);
		pw.println("Current date: " + date);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
