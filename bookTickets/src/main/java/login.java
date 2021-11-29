

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");

		String user=request.getParameter("uname");
		String pass=request.getParameter("pwd");
		HttpSession sess = request.getSession();
		sess.setAttribute("name", user);
		if(user.equals("root")&&pass.equals("toor")) {
			RequestDispatcher rd = request.getRequestDispatcher("Book");
			rd.forward(request, response);
		}   
        else {
        	pw.println("Sorry!Looks like your username or password are incorrect!");
        	RequestDispatcher rd= request.getRequestDispatcher("/login.html");
        	rd.include(request, response);
        }
		 pw.close();
	}

}
