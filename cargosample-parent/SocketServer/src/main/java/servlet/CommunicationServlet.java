package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import startcontrol.SocketServer;

/**
 * Servlet implementation class CommunicationServlet
 */
public class CommunicationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("helloworld");
		response.getWriter().flush();
		SocketServer server = new SocketServer();
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
