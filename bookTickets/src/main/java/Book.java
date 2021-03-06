

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String seat = request.getParameter("seat");
        String city = request.getParameter("city");
        String date=request.getParameter("date");
        String movie=request.getParameter("movie");
        HttpSession sess=request.getSession(false);
        String name = (String) sess.getAttribute("name");
        HttpSession sess1=request.getSession();
        sess1.setAttribute("seats", seat);
        sess1.setAttribute("city", city);
        sess1.setAttribute("date", date);
        sess1.setAttribute("movie", movie);
        try {
            out.println("<html>");
            out.println("<body>");
            out.println("<center>");
            out.println("<h2>Hello! "+name+"</h2>");
            out.println("<form method = 'post' action = 'Booked'>");
            out.println("<h1><u>Fill The Form Below To Book A Ticket<u></h1>");
            out.println("<table><tr>");
            out.println("<th>City:</th>");
            out.println("<td><input type = 'text' name = 'city'></td></tr><br>");
            out.println("<th>Date:</th>");
            out.println("<td><input type = 'date' name = 'date'></td></tr><br>");
            out.println("<th>Movie:</th>");
            out.println("<td><input type = 'text' name = 'movie'></td></tr><br>");
            out.println("<th>Seats:</th>");
            out.println("<td><input type = 'text' name = 'seat'></td></th></tr></table><br>");
            out.println("<input type = 'submit' value = 'Book'>");
            out.println("</form>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println(e);
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
